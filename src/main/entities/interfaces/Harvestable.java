package entities.interfaces;
import entities.Player;

/**
 * Interface for an itneractable you can harvest
 */
public interface Harvestable {
    String harvest(Player player);

    void addInfo();
}
