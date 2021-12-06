package usecases;

import entities.Interactable;
import entities.InteractableProperties;
import entities.Variable;
import entities.interfaces.ThrowableTarget;
import entities.weapons.ShootableWeapon;

import java.util.HashMap;

/**
 * Use case to shoot an entity
 */
public class Shoot {
    public String shootAction(HashMap<String, Interactable> args) {
        String shotObject = "shoot_obj"; String target = "target";
        if(args.get(shotObject) instanceof ShootableWeapon && args.get(target) instanceof ThrowableTarget) {
            ThrowableTarget throwTarget = (ThrowableTarget) args.get(target);
            ShootableWeapon shot = (ShootableWeapon) args.get(shotObject);
            if(shot.getAmmoCount() > 0) {
                shot.spendAmmo(1);
                Variable hitProbVar = shot.getProperty(InteractableProperties.HIT_PROB.name());
                int hitProb = hitProbVar.getInteger();
                if(hitProb / 100.0 > Math.random()){
                    return throwTarget.handleHit(args.get(shotObject));
                }
                return "Your shot missed";
            }
        }

        System.out.println(args.get(shotObject));
        if(!(args.get(shotObject) instanceof ShootableWeapon)){
            return "You cannot shoot that";
        }
        if(((ShootableWeapon) args.get(shotObject)).getAmmoCount() <= 0){
            return "You're out of ammunition";
        }


        return "You cannot shoot something at that";
    }
}
