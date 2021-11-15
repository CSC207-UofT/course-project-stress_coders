package entities;

import java.util.List;
import java.util.Scanner;

/**
 * entities.Goblin class, an enemy that speaks to you. The entities.Goblin prompts the user to enroll in his mini-game for some reward
 * but will only do so with a magic message! The entities.Goblin's reward is up to the specific circumstance.
 */
public class RiddleGoblin extends Goblin {
    private String magic_message; private String riddle; private String answer; private Player player;
    private List<String> hints;
    private int currHint = 0;

    /**
     * Constructor
     * @param id
     * @param p
     *
     */
    public RiddleGoblin(String id, Player p) {
        super(id, "This is a riddle goblin. You can talk to it and answer its riddle for a reward!",
                "To use this command use talk_to: receiver=[receiver_name]");
        this.player = p;
    }

    // To not make testing a pain, I split up the methods for settings hints and answer
    public void setHints(List<String> hints) {
        this.hints = hints;
    }

    /**
     * Set the riddle and answer
     * @param riddle
     * @param answer
     */
    public void setRiddleInfo(String magic_message, String riddle, String answer){
        this.magic_message = magic_message;
        this.riddle = riddle;
        this.answer = answer;
    }

    /**
     * Speak from Talklable interface, in this case the entities.Goblin does not speak on his own
     * @return
     */
    @Override
    public String speak(){
        return "";
    }

    /**
     * Listen and Respond from Talkable interface
     * The entities.Goblin listens for the magic message, then responds with a mini-game
     * @return
     */
    @Override
    public String listenAndRespond(){
        return minigame();
    }

    /**
     * This is the goblin's minigame. To play, you tell him is magic message, then correctly answer his riddle
     * to reap the rewards.
     * @return
     */
    private String minigame() {
        System.out.println("Answer my riddle!\n" + this.riddle);
        while (!this.isCompleted()) {
            // start mini-game
            Scanner lineIn = new Scanner(System.in);
            System.out.println("Here's a hint: " + hints.get(currHint));
            currHint++;
            if (currHint == hints.size()) {
                currHint = 0;
            }
            String userResponse = lineIn.nextLine();
            if (userResponse.equals(this.answer)) {
                System.out.println("Correct!\n" + this.reward());
            } else {
                System.out.println("Wrong! Try Again!");
            }
        }
        return "Congrats on solving the riddle!";
    }

    private Player getPlayer() {
        return this.player;
    }
    /**
     * Reward the player by increasing their health
     * @return
     */
    private String reward() {
        Player player = this.getPlayer();
        player.setHealthPoints(player.getHealthPoints() + 20);
        this.setCompleted(true);
        return player.getId() + " health points increase by 20!";
    }
    public String getAnswer() {
        return answer;
    }

    public String getMagic_message(){
        return magic_message;
    }

    public String getRiddle(){
        return riddle;
    }
}
