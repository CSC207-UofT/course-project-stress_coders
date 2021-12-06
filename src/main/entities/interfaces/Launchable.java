package entities.interfaces;

import entities.weapons.Axe;

/**
 * Interface for a launchable interactable.
 *
 * A launchable interactable is an interactable whose main purpose is to launch or release objects.
 *
 * Enforce the existence of the appropriate methods for all launchable objects.
 *
 * @see entities.minigames.Catapult
 * For an example

 */
public interface Launchable {

    /**
    all launchable interactables should have a boolean release method,
    indicating whether or not the object launch was successful or not.
    **/
    boolean launch();
}
