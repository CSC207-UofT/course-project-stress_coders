package interfaces;

/**
 * This interface is for any game object that can be thrown (i.e. an axe)
 */
public interface ThrowableObject {
    /** This method throws the object
     * @param hitProb this is the probability of what you throw hitting the target ahead of it
     *                (i.e. prob of hitting an enemy)
     */
    String throwObj();
}
