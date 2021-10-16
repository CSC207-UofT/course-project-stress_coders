package constants;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CommandConstants {
    public static final Map<String, String> ALL_COMMANDS = new HashMap<>();

    static {
        ALL_COMMANDS.put(null, null); // Replace null with whatever command is needed
        ALL_COMMANDS.put("pick up", "Command to pick up Weapon, you need this butter fingers, keep it safe!");
        ALL_COMMANDS.put("stab", "Command to stab (aim well padawan)");
        ALL_COMMANDS.put("block", "Command to block with all your might! Captain America would be proud!");
        ALL_COMMANDS.put("exit", "Command to exit the game, we hope you've had fun, please come back :D");
        ALL_COMMANDS.put("throw", "Throw your weapon like a big boy Canadian LumberJack.");
    }

    public static boolean isCommand(String commandParsed) {
        return ALL_COMMANDS.containsKey(commandParsed);
    }

    public static Set<String> allCommands(){return ALL_COMMANDS.keySet();}

    public static String getCommandInfo(String command){return ALL_COMMANDS.get(command);}
}