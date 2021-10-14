package Constants;

import commands.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CommandConstants {
    public static final Map<String, String> ALL_COMMANDS = new HashMap<>();

    static {
        ALL_COMMANDS.put(null, null); // Replace null with whatever command is needed
        ALL_COMMANDS.put("Pick Up", "Command to pick up Weapon, you need this butter fingers, keep it safe!");
        ALL_COMMANDS.put("Stab", "Command to stab (aim well padawan)");
        ALL_COMMANDS.put("Block", "Command to block with all your might! Captain America would be proud!");
        ALL_COMMANDS.put("exit", "Command to exit the game, we hope you've had fun, please come back :D");
    }

    public static boolean isCommand(String commandParsed) {
        return ALL_COMMANDS.containsKey(commandParsed);
    }

    public static Set<String> allCommands(){return ALL_COMMANDS.keySet();}

    public static String getCommandInfo(String command){return ALL_COMMANDS.get(command);}
}