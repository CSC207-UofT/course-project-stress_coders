package usecases;

import entities.*;
import entities.minigames.Maze;

import java.util.HashMap;
import java.util.Scanner;

public class Move {

    public String moveAction(HashMap<String, Interactable> args){

        Maze m = ((Maze) args.get("maze"));
        Scanner lineIn = new Scanner(System.in);

        //Initialize the While loop's variables
        String choice;
        char currMove;
        double current_time_remaining;
        String currMoveResult = "false";
        while(!currMoveResult.equals("true")){
            current_time_remaining = (m.getTimer().getMaxTime() - m.getTimer().getCurrentTime())/1000;
            System.out.println("You currently have " + current_time_remaining + " seconds left.");
            System.out.println("Your path is " + m.getTraveledPath());
            System.out.println("What is your move? [input 'right', 'left', 'up', or 'down'] \n" +
                    "Type quit to flee, but you will lose your weapon");

            choice = lineIn.nextLine();
            if(choice.equals("quit")){
                currMove = 'x';
                m.getTimer().updateMaxTime(-m.getTimer().getMaxTime());
            } else {
                currMove = getMove(choice);
            }
            currMoveResult = m.move(currMove);

            if(currMoveResult.equals("time")){
                return "You ran out of time and got lost. Your weapon was broken hacking your way out.";
            } else if (currMoveResult.equals("path")) {
                m.getTimer().updateMaxTime(-30000);
                current_time_remaining = (m.getTimer().getMaxTime() - m.getTimer().getCurrentTime())/1000;
                System.out.println("You strayed from the path, and find yourself back where you were. \n" +
                        "You wasted 30 seconds and only have " + current_time_remaining + " seconds left!");
            }
        }
        return "You made it! You got 1000 currency!";
    }

    private char getMove(String choice) {
        switch (choice) {
            case "right":
                System.out.println("You moved right.");
                return 'r';

            case "left":
                System.out.println("You moved left.");
                return 'l';

            case "up":
                System.out.println("You moved up.");
                return 'u';

            case "down":
                System.out.println("You moved down.");
                return 'd';

            default:
                System.out.println("You moved in no direction!");
                return 'x';
        }
    }
}
