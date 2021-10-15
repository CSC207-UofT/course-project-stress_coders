package weaponiteminterfaces;
import characters.Player;

/**
 * This interface is for any game object that can be thrown (i.e. an axe)
 */
public interface ThrowableObject {
    /** This method throws the object
     */
    String throwObj(Player player);
}
