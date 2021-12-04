package usecases;

import interfaceadapters.ShootCommand;
import interfaceadapters.TalkToCommand;
import interfaceadapters.ThrowCommand;
import interfaceadapters.UnlockCommand;

import java.util.HashMap;

/*
Store the commands that can be called. This class is never instanced.
 */
public class CommandConstants {
    // Mapping of command keyword to the command object. I.e "throw": new Throw()
    public static HashMap<String, Command> COMMANDS = new HashMap<>();

    // Load all normal commands into the COMMANDS hashmap
    static {
        COMMANDS.put("talk_to", new TalkToCommand());
        COMMANDS.put("throw", new ThrowCommand());
        COMMANDS.put("chop", new Chop());
        COMMANDS.put("consume", new Consume());
        COMMANDS.put("unlock", new UnlockCommand());
        COMMANDS.put("spin", new Spin());
        COMMANDS.put("shoot", new ShootCommand());
        COMMANDS.put("hint", new Hint());
    }
    public Command getCommand(String input) {return COMMANDS.get(input);}
}
