package usecases;

import entities.*;
import entities.interfaces.ThrowableTarget;

import java.util.HashMap;

/**
Shoot command, shoot an obj to a given target.
The chance of hitting should be dictated by hitProbability property
Shot objs must have a damage property

If the shot obj hits:
An enemy should be damaged by the damage of the shootableWeapon

 **/

public class Shoot extends Command{

    @Override
    public String execute(HashMap<String, Interactable> args) {
        String shotObject = "obj"; String target = "target";
        if(args.get(shotObject) instanceof ShootableWeapon && args.get(target) instanceof ThrowableTarget) {
            ThrowableTarget throwTarget = (ThrowableTarget) args.get(target);
            ShootableWeapon shot = (ShootableWeapon) args.get(shotObject);
            if(shot.getAmmoCount() > 0) {
                shot.spendAmmo(1);

                float hitProbVar = shot.getHitProb();
                int hitProb = Math.round(hitProbVar);

                if (hitProb / 100.0 > Math.random()) {
                    return throwTarget.handleHit(args.get(shotObject));
                }

                return "It missed";
            }
        }

        System.out.println(args.get(shotObject));
        if(!(args.get(shotObject) instanceof ShootableWeapon)){
            return "You cannot shoot that";
        }
        if(((ShootableWeapon) args.get(shotObject)).getAmmoCount() == 0){
            return "You're out of ammunition";
        }


        return "You cannot shoot something at that";
    }
}