package usecases;

import entities.Interactable;
import entities.InteractableProperties;
import entities.Variable;
import entities.interfaces.ThrowableTarget;
import entities.weapons.SwingableWeapon;

import java.util.HashMap;

public class Swing {
    public String shootAction(HashMap<String, Interactable> args) {
        String swungObject = "swing_obj"; String target = "target";
        if(args.get(swungObject) instanceof SwingableWeapon && args.get(target) instanceof ThrowableTarget) {

            ThrowableTarget throwTarget = (ThrowableTarget) args.get(target);
            SwingableWeapon shot = (SwingableWeapon) args.get(swungObject);

                Variable hitProbVar = shot.getProperty(InteractableProperties.HIT_PROB.name());
                int hitProb = hitProbVar.getInteger();

                if(hitProb / 100.0 > Math.random()){
                    return throwTarget.handleHit(args.get(swungObject));
                }

                return "You missed!";

        }

        System.out.println(args.get(swungObject));
        if(!(args.get(swungObject) instanceof SwingableWeapon)){
            return "That's not a weapon you can swing!";
        }

        return "You cannot swing something at that";
    }
}
