package Constants;

import commands.*;

import java.util.HashSet;
import java.util.Set;

public class CommandConstants {
    public static final Set<String> ALL_COMMANDS = new HashSet<>();

    static {
        ALL_COMMANDS.add(null); // Replace null with whatever command is needed
        ALL_COMMANDS.add("Pick Up");
        ALL_COMMANDS.add("Stab");
        ALL_COMMANDS.add("Block");
        ALL_COMMANDS.add("exit");
    }

    public static boolean isCommand(String commandParsed) {
        return ALL_COMMANDS.contains(commandParsed);
    }
}