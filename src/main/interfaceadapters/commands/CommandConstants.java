package interfaceadapters.commands;

import interfaceadapters.commands.*;

import java.util.HashMap;

/*
Store the commands that can be called. This class is never instanced.
 */
public class CommandConstants {
    // Mapping of command keyword to the command object. I.e "throw": new Throw()
    public static final HashMap<String, Command> COMMANDS = new HashMap<>();

    // Load all normal commands into the COMMANDS hashmap
    static {
        COMMANDS.put("talk_to", new TalkToCommand());
        COMMANDS.put("throw", new ThrowCommand());
        COMMANDS.put("chop", new ChopCommand());
        COMMANDS.put("consume", new ConsumeCommand());
        COMMANDS.put("unlock", new UnlockCommand());
        COMMANDS.put("spin", new SpinCommand());
        COMMANDS.put("shoot", new ShootCommand());
        COMMANDS.put("hint", new HintCommand());
        COMMANDS.put("roll", new DiceRollCommand());
        COMMANDS.put("launch", new LaunchCommand());
        COMMANDS.put("Swing", new SwingCommand());
        COMMANDS.put("duel", new DuelCommand());
        COMMANDS.put("race", new RaceCommand());

    }
    public Command getCommand(String input) {return COMMANDS.get(input);}
}
