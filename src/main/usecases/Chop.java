package usecases;

import entities.Axe;
import entities.Interactable;
import entities.Item;
import entities.Player;
import entities.interfaces.Harvestable;
import entities.interfaces.Talkable;

import java.beans.IntrospectionException;
import java.util.*;

/**
 * Chop command. Uses a tool to chop the target, assuming all is possible. Only for harvesting.
 */
public class Chop extends Command{
    private static List<Class> chopTools = Arrays.asList(new Class[] { Axe.class });

    @Override
    public String execute(HashMap<String, Interactable> args) {
        String tool = "tool"; String target = "target";
        if(args.get(target) instanceof Harvestable && anyInstanceOf(args.get(tool)) && args.get(tool) instanceof Item) {
            if(!(((Item) args.get(tool)).getHeldBy() == null)){
                return ((Harvestable) args.get(target)).harvest((Player) ((Item) args.get(tool)).getHeldBy());
            } else {
                return "You don't hold that";
            }
        } else {
            return "You cannot harvest that!";
        }
    }

    private boolean anyInstanceOf(Interactable obj) {
        for (Class cls : this.chopTools) {
            if (obj.getClass() == cls) {
                return true;
            }
        }
        return false;
    }
}
