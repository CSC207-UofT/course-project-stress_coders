package interfaceadapters;

import entities.Interactable;
import usecases.Command;
import usecases.Throw;

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

public class ThrowCommand extends Command {

    /*
    Execute first by determining if the objs are of valid type with the correct arguments
    Use the hitProb to determine if the throwable hits its target
    If so call the corresponding handleHit method.

    This command requires the args thrown_obj and target so the format would be
    throw: thrown_obj=axe1, target=enemy1
    for example
     */
    Throw throwAction;

    public ThrowCommand() {
        this.throwAction = new Throw();
    }

    @Override
    public String execute(HashMap<String, Interactable> args) {
        return throwAction.throwAction(args);
    }
}
