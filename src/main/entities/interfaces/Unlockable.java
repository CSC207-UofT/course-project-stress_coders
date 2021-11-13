package entities.interfaces;

public interface Unlockable {
    String userAttempt(String input);

    boolean isUnlocked();
}
