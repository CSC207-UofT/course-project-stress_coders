package usecases;

import entities.*;
import entities.interfaces.ThrowableTarget;

import java.util.HashMap;

/*
I used a modified version of the Throw code, this might not be working
 */

public class Shoot extends Command{

    @Override
    public String execute(HashMap<String, Interactable> args) {
        String shotObject = "obj"; String target = "target";
        if(args.get(shotObject) instanceof ShootableWeapon && args.get(target) instanceof ThrowableTarget) {
            ThrowableTarget throwTarget = (ThrowableTarget) args.get(target);
            ShootableWeapon shot = (ShootableWeapon) args.get(shotObject);
            shot.spendAmmo(1);

            float hitProbVar = shot.getHitProb();
            int hitProb = Math.round(hitProbVar);

            if(hitProb / 100.0 > Math.random()){
                return throwTarget.handleHit(args.get(shotObject));
            }

            return "It missed";
        }

        System.out.println(args.get(shotObject));
        if(!(args.get(shotObject) instanceof ShootableWeapon)){
            return "You shoot throw that";
        }

        return "You cannot shoot something at that";
    }
}
