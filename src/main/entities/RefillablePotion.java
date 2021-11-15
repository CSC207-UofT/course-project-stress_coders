package entities;

/*
A potion subclass that can be used multiple times, and refilled
By default, a refillable potion has 3 charges but that can be changed through a constructor
 */

public class RefillablePotion extends Potion{

    int uses = 3;

    public RefillablePotion(String id, int strength) {
        super(id);
        this.strength = strength;
        this.value = strength * VALUE_COEFFICIENT;
    }

    public RefillablePotion(String id, int strength, int uses) {
        super(id);
        this.strength = strength;
        this.value = strength * VALUE_COEFFICIENT;
        this.uses = uses;
    }

    @Override
    public String consume() {
        Player p = (Player) this.getHeldBy();

        if ((p != null) & (this.uses > 0)) {
            p.subConsumable(this);
            p.setHealthPoints(p.getHealthPoints() + this.getProperty(InteractableProperties.CONSUMABLE_REST_NAME.name()).getInteger());
            this.uses = this.uses - 1;
            return "You consumed 1" + this.getId();
        } else if (this.uses <= 0) {
            return "You don't have enough charges left!";
        } else {
            return "You don't hold this!";
        }
    }

    public void addCharge(int charge) {
        this.uses += charge;
    }

    public int getCharges() {
        return this.uses;
    }

    public void setCharges(int charges) {
        this.uses = charges;
    }
}
