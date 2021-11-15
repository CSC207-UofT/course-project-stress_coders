package frameworks;

import entities.*;
import entities.interfaces.Consumable;
import usecases.Command;
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
    private static final Set<String> SPECIAL_INPUTS = new HashSet<>(Arrays.asList("help", "progress",
            "display_objects", "consumeItem", "pick_up"));
    private static final Set<String> GAME_LENGTH_OPTIONS = new HashSet<>(List.of(new String[]{"short", "medium",
            "long", "test"}));

    private static final String genericHelp = "SOME GENERIC HELP FOR USER>> NEED TO ADD";
    public CommandLine() throws IOException {
        IDreader idReader = new IDreader();
        Encounter[] e = new Encounter[0];
        this.gameState = new GameState(e);
    }

    public CommandLine(GameState gs) throws IOException{
        IDreader idReader = new IDreader();
        this.gameState = gs;
    }

    // This method is just so we can load encounters and so on upon load, this needs to be done elsewhere
    // Talk to Shehzaad, have idea but need more details
    private GameState gameState() {
        return this.gameState;
    }
    /**
     Loop for retrieving user inputs and displaying the results, this is the user command line
     **/
    public void start() throws CloneNotSupportedException {
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
                this.gameState.setPlayerManager(this.playerState);
                requestAndBuild();
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
                    System.out.println(specialCommand(nextInput));
                }
                else {
                    System.out.println(callCommand(nextInput));
                }
            }
        }
    }

    public void requestAndBuild() throws CloneNotSupportedException {
        Scanner input = new Scanner(System.in);
        System.out.println("How long would you like the game to be (this will affect the length of the game) " +
                "select from 'short', 'medium', 'long'");
        System.out.print("$ ");
        String nextInput2 = input.nextLine();
        while (!GAME_LENGTH_OPTIONS.contains(nextInput2)) {
            System.out.println("Not a valid game length, please enter 'short', medium or 'long'");
            System.out.print("$ ");
            nextInput2 = input.nextLine();
        }
        BuilderSetup b = new BuilderSetup(playerState.getPlayer(), nextInput2);
        this.gameState.loadEncounters(b.build().toArray(new Encounter[0]));
    }

    /**
     * This method executes special commands that do not follow general formatting, such as help
     *
     * @param nextInput : input parsed in by the user that is received from start()
     * @return String indicating the command executed by user
     */

    public String specialCommand(String nextInput) {
        if (nextInput.equals("progress")) {
            for (String s : this.gameState().completedEncounters()) {
                return s;
            }
        } else if (nextInput.equals("help")) {
            return genericHelp + '\n' + this.gameState.getHelp();
        }
        else if (nextInput.equals("display_objects")) {
            // List the interactables available
            return this.gameState.getCurrent_encounter().listInteractables();
        } else if (nextInput.contains("pick_up")) {
            return specialPickUpCall(nextInput);
        }
        else if (nextInput.equals("consumeItem")) {
            return specialConsumeCall();
        }
        return "";
    }
    /**
     * We made pick_up a special command since we didn't want to unnecessarily pass in the player
     * using our current command system.
     */
    private String specialPickUpCall(String nextInput) {
        String[] splitString = nextInput.split(":");
        if(splitString.length != 2){ return "Unrecognized input"; }
        HashMap<String, Interactable> args = getInteractablesFromID(parseCommand(splitString[1]));
        String itemString = "item";
        if (this.gameState.getCurrent_encounter().containsObj(args.get(itemString))) {
            if (args.get(itemString) instanceof Consumable && !(args.get(itemString).isCompleted())) {
                this.playerState.getPlayer().addConsumable((Consumable) args.get(itemString));
                return "Added " + args.get(itemString).getId() + " to your items";
            } else if (args.get(itemString) instanceof Item) {
                ((Item) args.get(itemString)).setHeldBy(this.playerState.getPlayer());
                return "Added " + args.get(itemString).getId() + " to your items";
            } else {
                return "Cannot pick that up";
            }
        } else {
            return "Object does not exist";
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

    /**
    Take the @param: argToID string to string mapping from parseCommand and
    return the corresponding string to interactable mapping.
    I.e  {"weapon": "axe1"} returns {"weapon": AxeObj} provided there is an axe in the current encounter,
    return {"weapon": null} otherwise.
     **/
    public HashMap<String, Interactable> getInteractablesFromID(HashMap<String, String> argToID){
        HashMap<String, Interactable> idToInteractable = new HashMap<>();
        if (argToID.containsKey("consumable")) {
            idToInteractable.put("consumable", playerState.findConsumable(argToID.get("consumable")));
            return idToInteractable;
        }
        for(String key : argToID.keySet()){
            idToInteractable.put(key, gameState.getFromID(argToID.get(key)));
        }
        return idToInteractable;
    }

    /**
    Call the command dictated by the @param: user input. Command must be in format described above
    return a textual representation of the result of the command or an appropriate error message if the command
    call was invalid.
     **/
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
        String comm = splitString[command_id];

        return this.gameState.callCommand(comm, args);
    }

    public String specialConsumeCall() {
        System.out.println("Please enter the consumable of your choice from the given consumables in the format" +
                " consume: consumable= [consumable_id]");
        for (Consumable c: playerState.getAllConsumables()) {
            System.out.println(c.getId());
        }
        Scanner input = new Scanner(System.in);
        System.out.print("$ ");
        String nextInput2 = input.nextLine();
        Consume c = new Consume();
        String regex = ":";
        String[] splitString = nextInput2.split(regex);
        int args_id = 1;
        if(splitString.length != 2){
            return "Unrecognized input";
        }
        HashMap<String, String> argToID = parseCommand(splitString[args_id]);

        HashMap<String, Interactable> hh = getInteractablesFromID(argToID);

        return c.execute(hh);
    }
}