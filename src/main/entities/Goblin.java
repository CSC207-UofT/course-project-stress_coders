package entities;

import entities.interfaces.Talkable;

import java.util.Scanner;

/**
 * Goblin class, an enemy that speaks to you. The Goblin prompts the user to enroll in his mini-game for some reward
 * but will only do so with a magic message! The Goblin's reward is up to the specific circumstance.
 */
public class Goblin extends Enemy implements Talkable {
    private String magic_message; // This is the message he must hear before he starts the mini-game
    private String riddle; private String answer;
    /**
     * Constructor
     * @param id
     * @param player
     * @param magic_message
     */
    public Goblin(String id, Player player, String magic_message) {
        super(id, player);
        this.magic_message = magic_message;
    }

    /**
     * Set the riddle and answer
     * @param riddle
     * @param answer
     */
    public void setRiddleAnswer(String riddle, String answer){
        this.riddle = riddle;
        this.answer = answer;
    }

    /**
     * Speak from Talklable interface, in this case the Goblin does not speak on his own
     * @return
     */
    @Override
    public String speak(){
        return "";
    }

    /**
     * Listen and Respond from Talkable interface
     * The Goblin listens for the magic message, then responds with a mini-game
     * @param input
     * @return
     */
    @Override
    public String listenAndRespond(String input){
        if (input.equals(this.magic_message)) {
            if (!this.isCompleted()) {
                // start mini-game
                Scanner lineIn = new Scanner(System.in);
                System.out.println("Answer my riddle!\n" + this.riddle);
                String userResponse = lineIn.nextLine();
                if (userResponse.equals(this.answer)) {
                    return "Correct!\n" + this.reward();
                } else {
                    return "Wrong! Try Again!";
                }
            } else {
                return "Cannot play again hehe";
            }
        } else {
            return "I don't know what that means hehe";
        }
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
}
