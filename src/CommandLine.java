import java.util.HashMap;
import java.util.Scanner;

public class CommandLine {

    private GameState gameState;

    public CommandLine(){
        this.gameState = new GameState();
    }

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

    //Format for a command: throw: weapon=axe, target=thief
    // Return {"thrown_obj": "axe", "target": "thief"}
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

    public HashMap<String, Interactable> getInteractablesFromID(HashMap<String, String> argToID){
        HashMap<String, Interactable> idToInteractable = new HashMap<>();
        for(String key : argToID.keySet()){
            idToInteractable.put(key, gameState.getCurrent_encounter().getFromID(argToID.get(key)));
        }
        return idToInteractable;
    }

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
