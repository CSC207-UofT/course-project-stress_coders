package Constants;

import commands.*;

import java.util.HashSet;
import java.util.Set;

class CommandConstants {
    public static final Set<String> ALL_COMMANDS = new HashSet<>();

    static {
        ALL_COMMANDS.add(null); // Replace null with whatever command is needed
    }

    public static boolean isCommand(String commandParsed) {
        return ALL_COMMANDS.contains(commandParsed);
    }
}