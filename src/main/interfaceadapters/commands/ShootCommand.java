package interfaceadapters.commands;

import entities.*;
import usecases.Shoot;

import java.util.HashMap;

/**
Shoot command, shoot an obj to a given target.
 Ensure there's enough ammo and reduce ammo
The chance of hitting should be dictated by hitProbability property.
Shot objs must have a weight property.

If the shot obj hits:
An enemy should be damaged by the weight of the shootableWeapon

 **/

public class ShootCommand extends Command {

    final Shoot shootAction;

    public ShootCommand() {
        this.shootAction = new Shoot();
    }

    @Override
    public String execute(HashMap<String, Interactable> args) {
        return this.shootAction.shootAction(args);
    }
}
