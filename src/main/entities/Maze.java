package entities;

import entities.interfaces.Moveable;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Maze extends Interactable implements Moveable {

    private final int mazeLength;
    private int moveNum;
    private final String solutionPath;
    private String traveledPath;
    private final Timer timer;

    /**
     * Construct a new Maze which is randomized
     *
     * @param id the Maze's name ID
     */
    public Maze(String id){
        super(id, "move: next=[direction(left, right, up, down)]");
        Random r = new Random();
        this.mazeLength = r.nextInt(6) + 5;
        this.moveNum = 0;
        this.solutionPath = createPathRegex(this.mazeLength);
        this.traveledPath = "";
        this.timer = new Timer(0, mazeLength * 30000);
    }

    /**
     * Construct a new Maze whose length is set
     *
     * @param id the Maze's name ID
     * @param length the length of the maze which must be at least 5
     */
    public Maze(String id, int length){
        super(id, "move: next=[direction(left, right, up, down)]");
        this.mazeLength = length;
        this.moveNum = 0;
        this.solutionPath = createPathRegex(this.mazeLength);
        this.traveledPath = "";
        this.timer = new Timer(0, mazeLength * 30000);
    }

    /**
     * Construct a new Maze whose length and path is set
     *
     * @param id the Maze's name ID
     * @param length the length of the maze, which must be at least 5
     * @param solutionPath the path to escape the maze, which must be a regex
     */
    public Maze(String id, int length, String solutionPath){
        super(id, "move: next=[direction(left, right, up, down)]");
        this.mazeLength = length;
        this.moveNum = 0;
        this.solutionPath = solutionPath;
        this.traveledPath = "";
        this.timer = new Timer(0, mazeLength * 30000);
    }

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

    private boolean hasPathFailed(){
        String currRegex = this.solutionPath.substring(0, this.moveNum);
        Pattern pattern = Pattern.compile(currRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(this.traveledPath);
        return !matcher.find();
    }

    private boolean hasTravelledDistance() {
        return this.moveNum == this.mazeLength;
    }

    public String move(char nextMove){
        this.traveledPath = this.traveledPath + nextMove;
        timer.updateTime();
        this.moveNum ++;
        if (this.timer.hasTimeElapsed()){
            return "time";
        } else if(hasPathFailed()){
            this.moveNum = 0;
            return "path";
        }
        return Boolean.toString(hasTravelledDistance());
    }

}
