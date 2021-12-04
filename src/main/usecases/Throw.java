package usecases;

import entities.Interactable;
import entities.InteractableProperties;
import entities.Variable;
import entities.interfaces.Throwable;
import entities.interfaces.ThrowableTarget;

import java.util.HashMap;


/**
 * Use case to throw an entity
 */
public class Throw {
    public String throwAction(HashMap<String, Interactable> args) {
        String thrownObject = "throw_obj"; String target = "target";
        if(args.get(thrownObject) instanceof Throwable && args.get(target) instanceof ThrowableTarget) {
            ThrowableTarget throwTarget = (ThrowableTarget) args.get(target);
            Throwable thrown = (Throwable) args.get(thrownObject);

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

        if(!(args.get(thrownObject) instanceof Throwable)){
            return "You cannot throw that";
        }

        return "You cannot throw something at that";
    }
}
