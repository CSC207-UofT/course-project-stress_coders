package entities.interfaces;

/**
 * Enforce the existence of the appropriate instance variables for all throwable objects
 *
 * These instance variables are not stored in the interactables themselves since there would be no way to enforce
 * that all throwable interactables have implemented all required instance variables, or that they have a standard name
 * to be accessed by.
 *
 * This interface ensures that the required variables of throwable are added provided the methods are called, and they
 * are implemented correctly. If they are not when an incorrectly added variable is accessed an exception is thrown.
 *
 * All methods in the throwable class must be called once to add the required properties to the objects properties map:
 * @see entities.Interactable
 * For the map
 * @see entities.Axe
 * For an example
 *
 * The properties can be accessed later from the objects properties map using the standard variable names defined by:
 * @see entities.InteractableProperties
 */
public interface Throwable {
    /*
    Add the required params as per the above specification
     */
    void addHitProbability();

    void addWeight();

}
