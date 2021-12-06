package entities.minigames;

import entities.Interactable;
import entities.Unaffordable;
import entities.interfaces.Moveable;
import entities.characters.*;
import entities.weapons.*;
import usecases.Timing;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Maze extends Interactable implements Moveable {

    private final int mazeLength;
    private int moveNum;
    private final String solutionPath;
    private String traveledPath;
    private final Timer timer;
    private Player player;

    /**
     * Construct a new Maze which is randomized
     *
     * @param id the Maze's name ID
     * @param p the Player
     */
    public Maze(String id, Player p, Timing time){
        super(id, "move: maze=[maze_id]");
        Random r = new Random();
        this.mazeLength = r.nextInt(6) + 5;
        this.moveNum = 0;
        this.solutionPath = createPathRegex(this.mazeLength);
        this.traveledPath = "";
        this.timer = new Timer(0, this.mazeLength * 60000, time);
        this.player = p;
    }

    /**
     * Construct a new Maze whose length and path is set
     *
     * @param id the Maze's name ID
     * @param p the Player
     * @param solutionPath the path to escape the maze, which must be a regex
     * @param time the amount of time given
     */
    public Maze(String id, Player p, String solutionPath, double maxTime, Timing time){
        super(id, "move: maze=[maze_id]");
        this.mazeLength = solutionPath.length();
        this.moveNum = 0;
        this.solutionPath = solutionPath;
        this.traveledPath = "";
        this.timer = new Timer(0, maxTime, time);
        this.player = p;
    }

    /**
     * Generate a regex based on the length of the maze
     *
     * @param mazeLength the length of the maze
     * @return the path regex of the same length
     */
    private String createPathRegex(int mazeLength){
        if (mazeLength == 5) {
            return "rrullu";
        } else if (mazeLength == 6) {
            return "luruuu";
        } else if (mazeLength == 7){
            return "uruluru";
        } else if (mazeLength == 8){
            return "ulldluur";
        } else if (mazeLength == 9){
            return "luuurrddr";
        } else {
            return "urrdllluru";
        }
    }

    /**
     * Determine if the player's path no longer follows the maze's path
     *
     * @return true if the user's travelled path fails
     */
    public boolean hasPathFailed(String solutionPath, String traveledPath, int moveNum){
        String currRegex = solutionPath.substring(0, moveNum);
        Pattern pattern = Pattern.compile(currRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(traveledPath);
        return !matcher.find();
    }

    /**
     * Determine if the user has made it to the end of the maze and set the Maze to be completed if so
     *
     * @param mazeLength the length of the maze
     * @param moveNum the number of moves made
     * @return true if the number of moves the user made is the same as the maze length
     */
    public boolean hasTravelledDistance(int moveNum, int mazeLength) {
        if(moveNum == mazeLength){
            this.setCompleted(true);
            return true;
        }
        return false;
    }

    /**
     * Process the move made
     *
     * @param nextMove the next direction moved
     * @return "time" if time runs out, "path" if the path fails,
     * "false" if the path hasn't failed yet isn't complete and
     * "true" if the path is the solution
     */
    public String move(char nextMove){
        this.traveledPath = this.traveledPath + nextMove;
        timer.updateTime();
        this.moveNum ++;
        if (this.timer.hasTimeElapsed()){
            return "time";
        } else if(hasPathFailed(this.solutionPath, this.traveledPath, this.moveNum)){
            this.moveNum = 0;
            return "path";
        }
        return Boolean.toString(hasTravelledDistance(this.moveNum, this.mazeLength));
    }

    /**
     * Make the player lose his current weapon by replacing it with an Unaffordable one
     */
    public void playerLossesWeapon(){
        Weapon w = new Unaffordable();
        this.player.setWeapon(w);
    }

    /**
     * Reward the player 1000 currency
     */
    public void playerReward(){
        this.player.addCurrency(1000);
    }

    /**
     * Get the timer
     *
     * @return this maze's timer
     */
    public Timer getTimer() {
        return this.timer;
    }


    public void setPlayer(Player p) {
        this.player = p;
    }
}
