package interfaces;

/**
 * This interface is for any game object that can be collected (i.e. a potion)
 */
public interface Collectable {
    /** This method collects an object
     */
    default void collect() {}
}