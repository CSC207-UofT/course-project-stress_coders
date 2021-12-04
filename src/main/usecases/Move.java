package usecases;

import entities.*;

import java.lang.Character;
import java.util.HashMap;
import java.util.Scanner;

public class Move extends Command{

    @Override
    public String execute(HashMap<String, Interactable> args){

        Maze m = ((Maze) args.get("maze"));
        char currMove = 'x';
        System.out.println("What is you move?");
        Scanner lineIn = new Scanner(System.in);
        String choice = lineIn.nextLine();
        currMove = getMove(currMove, choice);
        String currMoveResult = m.move(currMove);
        while(!currMoveResult.equals("true")){
            System.out.println("What is you move?");
            choice = lineIn.nextLine();

            currMove = getMove(currMove, choice);
            currMoveResult = m.move(currMove);

            if(currMoveResult.equals("time")){
                m.playerLossesWeapon();
                return "You ran out of time and got lost. Your weapon was broken hacking your way out.";
            }
        }
        m.playerGainsMoney();
        return "You made it! You got 1000 currency!";
    }

    private char getMove(char move, String choice) {
        switch (choice) {
            case "right":
                System.out.println("You moved right.");
                move = 'r';

            case "left":
                System.out.println("You moved left.");
                move = 'l';

            case "up":
                System.out.println("You moved up.");
                move = 'u';

            case "down":
                System.out.println("You moved down.");
                move = 'd';
        }
        return move;
    }
}
