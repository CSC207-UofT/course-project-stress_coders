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

    @Override
    public void addInfo() {
        super.addProperty(InteractableProperties.RES_NAME.name(), new Variable("wood"));
        super.addProperty(InteractableProperties.RES_STORE_NAME.name(), new Variable(100));
        super.addProperty(InteractableProperties.RES_DIST_NAME.name(), new Variable(25));
    }

    public String harvest(Player player){
        if (!(this.isCompleted())) {
            int distribution = this.getProperty(InteractableProperties.RES_DIST_NAME.name()).getInteger();
            player.addInventory("wood", distribution);
            int storage = this.getProperty(InteractableProperties.RES_STORE_NAME.name()).getInteger();
            this.addProperty(InteractableProperties.RES_STORE_NAME.name(), new Variable(storage - distribution));
            return "Added " + distribution + " wood to " + player.getId() + " inventory";
        } else{
            return "No more left!";
        }
    }

}
