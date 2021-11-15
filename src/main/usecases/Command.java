package usecases;

import java.util.HashMap;
import java.util.List;
import entities.*;

/*
Framework for all commands same implementation as the command design pattern.
 */
public abstract class Command {
    // describes command function and valid syntax/parameters for the command
    /*
    Valid params that this function can be called with, stored in this format:
     {"thrown_obj": ["entities.interfaces.Throwable"],
     "target": ["entities.interfaces.ThrowableTarget",
     "entities.Enemy", "Tree"]}
     */
    private HashMap<String, List<String>> validParams;

    public Command(){}

    /*
    args maps argument name to entered value. I.e a throw command requires the args thrown_obj and a target,
    an eat command would require the arg edible_obj.
    args contains a mapping of these arguments to objs as entered by the player.

    If the player entered the incorrect args return an error message.
     */
    public abstract String execute(HashMap<String, Interactable> args);

    /*
    Return the command description
     */
}
