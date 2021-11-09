package usecases;

import entities.*;
import entities.interfaces.*;
import entities.interfaces.Throwable;
import entities.Interactable;
import entities.Command;

import java.util.HashMap;

public class Throw extends Command {

    // Params: entities.interfaces.Throwable weapon, entities.Interactable target
    @Override
    public String execute(HashMap<String, Interactable> args) {
        String thrownObject = "thrown_obj"; String target = "target";
        if(args.get(thrownObject) instanceof Throwable && args.get(target) instanceof ThrowableTarget) {
            ThrowableTarget throwTarget = (ThrowableTarget) args.get(target);
            Throwable thrown = (Throwable) args.get(thrownObject);

            if (thrown instanceof Item){
                Item thrownItem = (Item) thrown;
                thrownItem.setHeldBy(null);
            }

            Variable hitProbVar = ((Interactable) thrown).getProperty(InteractableProperties.HIT_PROB.name());
            int hitProb = hitProbVar.getInteger();

            if(hitProb / 100.0 > Math.random()){
                return throwTarget.handleHit(args.get(thrownObject));
            }

            return "You missed";
        }

        System.out.println(args.get(thrownObject));
        if(!(args.get(thrownObject) instanceof Throwable)){
            return "You cannot throw that";
        }

        return "You cannot throw something at that";
    }
}
