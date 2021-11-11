package frameworks;

import entities.*;
import interfaceadapters.*;
import usecases.Command;
import usecases.CommandConstants;

import java.util.HashMap;
import java.util.Scanner;

/*
Singleton that handles reading user input.
Contain the loop for receiving input, parse the input and call the appropriate command.
 */
public class CommandLine {

    // Required GameState object. CL must call commands that interact with the Encounter and gameState.
    private GameState gameState;

    public CommandLine(){
        this.gameState = new GameState();
    }

    /*
     Loop for retrieving user inputs and displaying the results
     */
    public void start(){
        boolean running = true;
        CommandConstants.loadCommands();

        while(running){
            Scanner input = new Scanner(System.in);
            System.out.println("What do you want to do next? ");
            String nextInput = input.nextLine();

            System.out.println(callCommand(nextInput));
        }
    }

    /**
    Parses the given @param: input by creating a mapping from argument name to argument value.
    Argument value is an ID of an interactable in the encounter
    Argument name is a param that the given command requires
    Both values are Strings
    The input must be in the following format: "command: arg1=value1, arg2=value2..., argN = valueN"
    @see usecases.Throw
    for an example.

    The expected return value is:
    {"arg1": "value1", "arg2": "value2", ..., "argN": "valueN"}
     **/
    public HashMap<String, String> parseCommand(String input){
        String regexParam = ",";
        input = input.trim();

        String[] argsWithParams = input.split(regexParam);
        String regexArgs = "=";

        HashMap<String, String> stringArgs = new HashMap<>();
        for(String args : argsWithParams){
            String[] sep = args.split(regexArgs);

            if(sep.length != 2){
                return new HashMap<>();
            }
            stringArgs.put(sep[0].trim(), sep[1].trim());
        }
        return stringArgs;
    }

    /*
    Take the @param: argToID string to string mapping from parseCommand and
    return the corresponding string to interactable mapping.

    I.e  {"weapon": "axe1"} returns {"weapon": AxeObj} provided there is an axe in the current encounter,
    return {"weapon": null} otherwise.
     */
    public HashMap<String, Interactable> getInteractablesFromID(HashMap<String, String> argToID){
        HashMap<String, Interactable> idToInteractable = new HashMap<>();
        for(String key : argToID.keySet()){
            idToInteractable.put(key, gameState.getCurrent_encounter().getFromID(argToID.get(key)));
        }
        return idToInteractable;
    }

    /*
    Call the command dictated by the @param: user input. Command must be in format described above

    return a textual representation of the result of the command or an appropriate error message if the command
    call was invalid.
     */
    public String callCommand(String input) {
        String regex = ":";
        String[] splitString = input.split(regex);
        int command_id = 0; int args_id = 1;

        if(splitString.length != 2){
            return "Unrecognized input";
        }
        HashMap<String, String> argToID = parseCommand(splitString[args_id]);
        HashMap<String, Interactable> args = getInteractablesFromID(argToID);

        Command command = CommandConstants.COMMANDS.get(splitString[command_id]);
        if(command != null){
            return command.execute(args);
        } else {
            return "Not a command";
        }
    }
}