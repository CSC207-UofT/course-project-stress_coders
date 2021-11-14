package usecases;

import entities.*;
import entities.interfaces.*;
import entities.interfaces.Throwable;
import entities.Interactable;

import java.util.HashMap;

/*
Throw command, throw an obj to a given target.
The chance of hitting should be dictated by hitProbability property
Thrown objs must have a weight property

If the thrown obj hits:
An enemy should be damaged by the weight of the throwable
A button should update its state to pushed.
...

Throwable objs must implement the properties hitProb and Weight
 */

public class Throw extends Command {

    /*
    Execute first by determining if the objs are of valid type with the correct arguments
    Use the hitProb to determine if the throwable hits its target
    If so call the corresponding handleHit method.

    This command requires the args thrown_obj and target so the format would be
    throw: thrown_obj=axe1, target=enemy1
    for example
     */
    @Override
    public String execute(HashMap<String, Interactable> args) {
        String thrownObject = "throw_obj"; String target = "target";
        if(args.get(thrownObject) instanceof Throwable && args.get(target) instanceof ThrowableTarget) {
            ThrowableTarget throwTarget = (ThrowableTarget) args.get(target);
            Throwable thrown = (Throwable) args.get(thrownObject);

            if (thrown instanceof Item){
                Item thrownItem = (Item) thrown;
//                thrownItem.setHeldBy(null);
            }
            /*
             The param that is the value of the thrown_obj arg for the throw command must have a hitProb
             in its properties hashmap
             */
            Variable hitProbVar = ((Interactable) thrown).getProperty(InteractableProperties.HIT_PROB.name());
            int hitProb = hitProbVar.getInteger();

            if(hitProb / 100.0 > Math.random()){
                return throwTarget.handleHit(args.get(thrownObject));
            }

            return "It missed";
        }

        System.out.println(args.get(thrownObject));
        if(!(args.get(thrownObject) instanceof Throwable)){
            return "You cannot throw that";
        }

        return "You cannot throw something at that";
    }
}
