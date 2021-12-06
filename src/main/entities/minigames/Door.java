package entities.minigames;

import entities.Interactable;
import entities.interfaces.Unlockable;

public abstract class Door extends Interactable implements Unlockable {

    String[] hints;
    String answer;
    public int currentHint;

    /**
     Construct a door object

     @param id the id of the door
     @param hints a list of hints to unlock the door
     @param initial initial text
     @param answer the answer to unlock the door
     **/
    public Door(String id, String[] hints, String initial, String answer){
        super(id, initial, "unlock: door=[door_id], hint: door=[door_id]");
        super.setCompleted(false);
        this.hints = hints;
        this.currentHint = 0;
        this.answer = answer;
    }

    /**
     Construct a door object

     @param id the id of the door
     @param hints a list of hints to unlock the door
     @param answer the answer to unlock the door
     */
    public Door(String id, String[] hints, String answer){
        super(id, "You're blocked by a door try to figure out its code using hints.",
                "unlock: door=[door_id]");
        super.setCompleted(false);
        this.hints = hints;
        this.currentHint = 0;
        this.answer = answer;

    }

    /**
     * Determine if the door is unlocked
     *
     * @return true if the door is unlocked
     */
    public boolean isUnlocked() {return super.isCompleted();}

    /**
     * Return the next hint
     *
     * @return a hint from hints
     */
    public String provideHint() {
        if (currentHint >= hints.length) {
            currentHint = 0;
        }
        String hint = hints[currentHint];
        currentHint++;
        return hint;
    }

    /**
     * Return a String of all the hints
     *
     * @return all the hints
     */
    public String viewAllHints() {
        String accum = "";
        for (String hint: hints) {
            accum = accum.concat(hint) + "\n";
        }
        return accum;
    }

    /**
     * Reset the current hint to 0
     */
    public void resetHints() {currentHint = 0;}

    /**
     * Determine whether a user's input unlocks the door or not
     *
     * @param input the user's input to unlock the door
     * @return whether the user unlocked the door or not
     */
    public String userAttempt(String input) {
        if (input.equals(answer)) {
            super.setCompleted(true);
            return "Nice! Your cracked the code.";
        }
        return "Oops! Incorrect code..try again.";
    }

    public Door(){}
}
