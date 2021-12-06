package entities.minigames;

import entities.Interactable;
import entities.characters.Character;
import entities.characters.Enemy;
import entities.characters.Player;
import entities.interfaces.Raceable;

import java.util.ArrayList;
import java.util.Random;

public class HorseRace extends Interactable implements Raceable {


    Player player;

    /**
     * Construct a HorceRace
     *
     * A horse race is a race between several characters,
     * where they race on horses.
     *
     * The player has to press 'w' more than
     *
     *
     * @param id the HorseRace object's id
     * @param p The current player participating in the horce race.
     *
     *
     *  TODO: stop using hashmaps and instead shuffle the array to select a random
     *        array for leaderboard purposes, and select the winner when its not you using
     *        that shuffled array
     *
     *
     * representation invariants
     *
     *       numRacers >= 1
     */
    private int[] racers;
    private int numRacers;
    private Character[] output;
   // private HashMap<Character, Integer> yahToCharacter;
    private ArrayList<Character> arr = new ArrayList<>();
    private int yahCount;
    private int numYahsNeeded;


    public HorseRace(String id, Player p, int numRacers) {
        super(id, "race: HorseRace=[HorseRace_id]");
        player = p;
        this.numRacers = numRacers;
        this.racers = new int[this.numRacers];
        this.output = new Character[this.numRacers];
        // this.yahToCharacter = new HashMap<Character, Integer>();
        // We set 0 to represent

//        for(int i=0; i<this.numRacers; i++) {
//            this.arr.add(i);
//        }
        this.arr.add(p);
        // Populate the HashMap and Character array
        for(int i=1; i <this.numRacers; i++) {
            Character x;
            x = new Enemy(Integer.toString(i), p, 50);
            this.arr.add(x);
            // this.output[i] = x;
            // yahToCharacter.put(x, 0);
        }


        Random r = new Random();
        this.numYahsNeeded = r.nextInt(10);

    }



    @Override
    public ArrayList<Character> getOutput() {
        return this.arr;
    }

    @Override
    public Character getRaceWinner() {
        return this.getOutput().get(0);
    }

    public String getIdRaceWinner() {
        return this.getRaceWinner().getId();
    }

    /**
     * A "Yah" is what you say to get the horse moving
     *
     * You press 'W' key, which increments the yahCount by 1
     *
     * If you hit W the correct number of times, then you win.
     *
     * The number of times is determined randomly.
     *
     * A horse race is a race between several characters,
     * where they race on horses.
     *
     * The player has to press 'w' greater than or equal the
     * numYahsNeeded to end the race.
     *
     * The interactable is setCompleted only when this happens
     *
     */

    @Override
    public void incrementYah() {
        this.yahCount++;
    }

    @Override
    public boolean playerWon() {
        if (this.yahCount == this.numYahsNeeded) {
            return true;
        }
        return false;
    }

    @Override
    public boolean raceOver() {
        if (this.yahCount >= this.numYahsNeeded) {
            this.setCompleted(true);
            return true;
        }
        return false;
    }

    public HorseRace(){}

    public void setPlayer(Player player) {
        this.player = player;
    }
}
