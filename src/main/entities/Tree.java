package entities;

import entities.interfaces.Harvestable;

/**
 * Tree interactabole. When harvested it rewards the harvester with the wood reource.
 */
public class Tree extends Interactable implements Harvestable {
    public Tree(String id){
        super(id);
        addInfo();
    }

    /**
     * interface method
     */
    @Override
    public void addInfo() {
        super.addProperty(InteractableProperties.RES_NAME.name(), new Variable("wood"));
        super.addProperty(InteractableProperties.RES_STORE_NAME.name(), new Variable(100));
    }

    /**
     * Add to the players inventory, the new resources
     * @param player
     * @return
     */
    public String harvest(Player player, int toolDamage){
        if (!(this.isCompleted())) {
            int remaining = this.getProperty(InteractableProperties.RES_STORE_NAME.name()).getInteger();
            int returnAmount = Math.min(remaining, toolDamage);
            player.addInventory("wood", returnAmount);
            this.addProperty(InteractableProperties.RES_STORE_NAME.name(), new Variable(remaining - returnAmount));
            if (remaining - returnAmount <= 0) {
                this.setCompleted(true);
            }

            return "Added " + returnAmount + " wood to " + player.getId() + " inventory";
        } else{
            return "No more left!";
        }
    }

}
