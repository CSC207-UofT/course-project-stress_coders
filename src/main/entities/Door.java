package entities;

public abstract class Door extends Interactable {

    String[] hints;
    String answer;
    int currentHint;

    public Door(String id, String[] hints, String initial, String answer){
        super(id, initial);
        super.setCompleted(false);
        this.hints = hints;
        this.currentHint = 0;
        this.answer = answer;
    }

    public Door(String id, String[] hints, String answer){
        super(id, "You're blocked by a door try to figure out its code using hints.");
        super.setCompleted(false);
        this.hints = hints;
        this.currentHint = 0;
        this.answer = answer;

    }

    public boolean isUnlocked() {return super.isCompleted();}

    public String provideHint() {
        if (currentHint >= hints.length) {
            return "Sorry you're out of hints!";
        }
        String hint = hints[currentHint];
        currentHint++;
        return hint;
    }

    public String viewAllHints() {
        String accum = "";
        for (String hint: hints) {
            accum = accum.concat(hint) + "\n";
        }
        return accum;
    }

    public void resetHints() {currentHint = 0;}

    public String userAttempt(String input) {
        if (input.equals(answer)) {
            super.setCompleted(true);
            return "Nice! Your cracked the code.";
        }
        return "Oops! Incorrect code..try again.";
    }
}
