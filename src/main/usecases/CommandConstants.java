package usecases;

import java.util.HashMap;

/*
Store the commands that can be called. This class is never instanced.
 */
public class CommandConstants {
    // Mapping of command keyword to the command object. I.e "throw": new Throw()
    public static HashMap<String, Command> COMMANDS = new HashMap<>();

    // Load all normal commands into the COMMANDS hashmap
    public static void loadCommands(){
        COMMANDS.put("talk_to", new TalkTo());
        COMMANDS.put("throw", new Throw());
        COMMANDS.put("chop", new Chop());
        COMMANDS.put("consume", new Consume());
        COMMANDS.put("unlock", new Unlock());
        COMMANDS.put("spin", new Spin());
        COMMANDS.put("shoot", new Shoot());

    }

    public void add_command(String keyword, Command command){
        COMMANDS.put(keyword, command);
    }

    public Command getCommand(String input) {return COMMANDS.get(input);}
}
