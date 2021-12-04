package entities.interfaces;
import entities.characters.Player;

/**
 * Interface for an interactable you can harvest
 */
public interface Harvestable {
    String harvest(Player player, int toolDamage);

    void addInfo();
}
