package entities;

import entities.interfaces.ThrowableTarget;


public class Animal extends Character implements ThrowableTarget {

    private Player player;

    /**
     * Constructs a new Animal object
     *
     * @param id the id of the Animal
     * @param health the number of health points
     * @param player the player
     */
    public Animal(String id, int health, Player player) {
        super(id);
        this.player = player;
        super.setHealthPoints(health);
    }


    /**
     @see ThrowableTarget for details
     Damage the animal by the weight of the object

     return "your {weapon} hits {animal name} for {weight} damage"
     if you killed the animals also return "You killed the beast and received {id} meat!"
     **/
    @Override
    public String handleHit(Interactable throwable) {
        int weight = throwable.getProperty(InteractableProperties.WEIGHT.name()).getInteger();
        setHealthPoints(getHealthPoints() - weight);
        if (isDead()) {
            Meat meat = new Meat(getId() + " meat");
            meat.addRestorationValue();
            player.addConsumable(meat);
            return "Your "+ throwable.getId() + " hits " + getId() + " for " + weight + " damage! You killed the beast " +
                    "and received " + getId() + " meat";
        }
        else {
            return  "Your "+ throwable.getId() + " hits " + getId() + " for " + weight + " damage!\n";
        }
    }


}
