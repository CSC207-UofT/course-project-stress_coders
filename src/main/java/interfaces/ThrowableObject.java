package interfaces;

/**
 * This interface is for any game object that can be thrown (i.e. an axe)
 */
public interface ThrowableObject {
    /** This method throws the object a given distance
     * @param distance integer quantity that describes how far obj should be thrown
     */
    default void throwObj(int distance) {}
}
