package frameworks;

import entities.*;
import entities.interfaces.Consumable;
import interfaceadapters.*;
import usecases.*;

import java.io.IOException;
import java.util.*;

/*
Singleton that handles reading user input.
Contain the loop for receiving input, parse the input and call the appropriate command.
 */
public class CommandLine {

    // Required GameState object. CL must call commands that interact with the Encounter and gameState.
    private GameState gameState;
    private PlayerManager playerState;
    private static final Set<String> SPECIAL_INPUTS = new HashSet<>(Arrays.asList("help", "progress", "docu",
            "display_objects", "pick_up"));
    private static final String genericHelp = "SOME GENERIC HELP FOR USER>> NEED TO ADD";
    public CommandLine() throws IOException {
        IDreader idReader = new IDreader();
        Encounter[] e = new Encounter[0];
        this.gameState = new GameState(e);
    }

    // This method is just so we can load encounters and so on upon load, this needs to be done elsewhere
    // Talk to Shehzaad, have idea but need more details
    private GameState gameState() {
        return this.gameState;
    }
    /*
     Loop for retrieving user inputs and displaying the results
     */
    public void start() {
        boolean running = true;
        boolean firstRun = true;
        CommandConstants.loadCommands();

        while(running) {
            if (firstRun) {
                firstRun = false;
                System.out.println("Please choose a player name: ");
                Scanner input = new Scanner(System.in);
                System.out.print("$ ");
                String nextInput = input.nextLine();
                this.playerState = new PlayerManager(nextInput);
                System.out.println(this.gameState.requestEncounter());
                // Print some sort of welcome and instructions here
            } else {
                Scanner input = new Scanner(System.in);
                System.out.print("$ ");
                String nextInput = input.nextLine();
                if (nextInput.equals("exit")) {
                    running = false;
                }
                else if (SPECIAL_INPUTS.contains(nextInput)) {
                    specialCommand(nextInput);
                }
                else {
                    System.out.println(callCommand(nextInput));
                }
            }
        }
    }

    public String specialCommand(String nextInput) {
        if (nextInput.equals("progress")) {
            for (String s : this.gameState().completedEncounters()) {
                return s;
            }
        } else if (nextInput.equals("help")) {
            return genericHelp + '\n' + this.gameState.getHelp(playerState.getPlayer());
        } else if (nextInput.contains("docu")) {
            // View the description of a command
            nextInput = nextInput.trim();
            String regex = ":";
            String[] splitString = nextInput.split(regex);
            if (splitString.length != 2) { return "Invalid input, see documentation for this command"; }
            return CommandConstants.COMMANDS.get(splitString[1]).help();
        } else if (nextInput.equals("display_objects")) {
            // List the interactables available
            return this.gameState.getCurrent_encounter().listInteractables();
        } else if (nextInput.contains("pick_up")) {
            /**
             * We made pick_up a special command since we didn't want to unnecessarily pass in the player
             * using our current command system.
             */
            String[] splitString = nextInput.split(":");
            if(splitString.length != 2){ return "Unrecognized input"; }
            HashMap<String, Interactable> args = getInteractablesFromID(parseCommand(splitString[1]));
            String itemString = "item";
            if (args.get(itemString) instanceof Consumable) {
                this.playerState.getPlayer().addConsumable((Consumable) args.get(itemString));
            } else if (args.get(itemString) instanceof Item) {
                ((Item) args.get(itemString)).setHeldBy(this.playerState.getPlayer());
            }
        }
        return "";
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
        if(command == null){
            return "Not a command";
        }
        return this.gameState.callCommand(input, args);
    }
}