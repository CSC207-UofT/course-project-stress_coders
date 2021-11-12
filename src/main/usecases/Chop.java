package usecases;

import entities.*;
import entities.interfaces.CanChop;
import entities.interfaces.Harvestable;
import entities.interfaces.Talkable;

import java.beans.IntrospectionException;
import java.util.*;

/**
 * Chop command. Uses a tool to chop the target, assuming all is possible. Only for harvesting.
 */
public class Chop extends Command{

    /**
     * args["tool"] is the tool you chop with
     * args["target"] is the object you are chopping
     * execute: the axe doesn't chop the tree, but it is used to 'harvest' (chopping) and the tree delegates what happens
     *          this way, we don't use an unnecessary method in Axe and keep it simple.
     * NOTE: we did not use interfaces for objects that "can chop" because this would be an empty interface, as there is
     *       no method it needs to enforce.
     * @param args
     * @return
     */
    @Override
    public String execute(HashMap<String, Interactable> args) {
        String tool = "tool"; String target = "target";
        if(args.get(target) instanceof Harvestable && args.get(tool) instanceof CanChop && args.get(tool) instanceof Item) {
            if(!(((Item) args.get(tool)).getHeldBy() == null)){
                // harvest the tree using the player that's holding the axe
                return ((Harvestable) args.get(target)).harvest((Player) ((Item) args.get(tool)).getHeldBy(),
                        args.get(tool).getProperty(InteractableProperties.CHOP_DMG_NAME.name()).getInteger());
            } else {
                return "You don't hold that";
            }
        } else {
            return "You cannot harvest that!";
        }
    }
}
