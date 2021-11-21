package entities.interfaces;

/**
 * Interface for an interactable for duels where a winner between 2 must be decided.
 */
public interface Duelable {
    // determines if the player won the duel or not.
    boolean playerWon();
}
