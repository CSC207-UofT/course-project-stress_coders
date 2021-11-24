package entities.interfaces;


import entities.Character;

/**
 * Interface for an interactable for races where it returns an array of the winners.
 * In the array, at element 0 the winner is stored, and the loser is in the last element of the array.
 */
public interface Raceable {
    Character[] getOutput();
    Character getRaceWinner();
    String getIdRaceWinner();
    void incrementYah();
    boolean playerWon();
    boolean raceOver();
}
