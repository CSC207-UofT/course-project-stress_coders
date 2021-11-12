package entities;

import java.util.Scanner;

/**
 * entities.Goblin class, an enemy that speaks to you. The entities.Goblin prompts the user to enroll in his mini-game for some reward
 * but will only do so with a magic message! The entities.Goblin's reward is up to the specific circumstance.
 */
public class RiddleGoblin extends Goblin {
    private String magic_message; private String riddle; private String answer;
    /**
     * Constructor
     * @param id
     * @param player
     */
    public RiddleGoblin(String id, Player player, int value) {
        super(id, player, value);
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
     * @param input
     * @return
     */
    @Override
    public String listenAndRespond(String input){
        return minigame(input);
    }

    /**
     * This is the goblin's minigame. To play, you tell him is magic message, then correctly answer his riddle
     * to reap the rewards.
     * @param input
     * @return
     */
    private String minigame(String input) {
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
            return "That is not my magic word, say my magic word!";
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
