package entities;

import usecases.Throw;

import java.util.HashMap;

public class CommandConstants {
    public static HashMap<String, Command> COMMANDS = new HashMap<>();

    public static void loadCommands(){
        COMMANDS.put("throw", new Throw());
    }

    public void add_command(String keyword, Command command){
        COMMANDS.put(keyword, command);
    }
}
