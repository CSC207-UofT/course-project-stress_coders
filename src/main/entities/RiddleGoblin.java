package entities;

import java.util.Scanner;

/**
 * entities.Goblin.txt class, an enemy that speaks to you. The entities.Goblin.txt prompts the user to enroll in his mini-game for some reward
 * but will only do so with a magic message! The entities.Goblin.txt's reward is up to the specific circumstance.
 */
public class RiddleGoblin extends Goblin {

    private String magic_message;
    private String riddle;
    private String answer;

    /**
     * Construct a RiddleGoblin
     *
     * @param id the RiddleGoblin object's id
     * @param player the player the riddle goblin is interacting with
     */
    public RiddleGoblin(String id, Player player, int value) {
        super(id, player, value);
    }

    /**
     * Set the riddle and answer
     *
     * @param riddle the riddle to be solved
     * @param answer the answer to the riddle
     */
    public void setRiddleInfo(String magic_message, String riddle, String answer){
        this.magic_message = magic_message;
        this.riddle = riddle;
        this.answer = answer;
    }

    /**
     * Speak from Talklable interface, in this case the entities.Goblin does not speak on his own
     *
     * @return the empty string since the
     */
    @Override
    public String speak(){
        return "";
    }

    /**
     * Listen and Respond from Talkable interface
     * The entities.Goblin listens for the magic message, then responds with a mini-game
     *
     * @param input the input to listen to
     * @return the proper response
     */
    @Override
    public String listenAndRespond(String input){
        return minigame(input);
    }

    /**
     * This is the goblin's minigame. To play, you tell him his magic message, then correctly answer his riddle
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
     * Reward the player for solving the puzzle by increasing their health
     *
     * @return a string containing the reward for solving the puzzle
     */
    private String reward() {
        Player player = this.getPlayer();
        player.setHealthPoints(player.getHealthPoints() + 20);
        this.setCompleted(true);
        return player.getId() + " health points increase by 20!";
    }

    /**
     * Get the answer for the puzzle
     *
     * @return the answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Get the magic message needed to start the puzzle
     *
     * @return the magic message
     */
    public String getMagic_message(){
        return magic_message;
    }

    /**
     * Get the riddle to be solved
     *
     * @return the riddle
     */
    public String getRiddle(){
        return riddle;
    }
}
