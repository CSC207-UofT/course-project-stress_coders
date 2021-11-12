package entities;

/*
Generic character class for shared behaviour between living things.
I.e. NPCs, Enemies and the player.
 */
public abstract class Character extends Interactable {
    private int healthPoints;
    private int maxHealthPoints = 100;

    public Character(String id) {
        super(id, "");
    }

    public void setHealthPoints(int healthPoints) {
        if (healthPoints <= maxHealthPoints) {
            this.healthPoints = healthPoints;
        }
        else {
            this.healthPoints = this.maxHealthPoints;
        }
    }

    public void setMaxHealthPoints(int maxHP) {
        this.maxHealthPoints = maxHP;
    }
    public int getHealthPoints(){
        return this.healthPoints;
    }

    public boolean isDead() {return this.healthPoints <= 0;}

    public void addModifier(float weight){
        this.healthPoints *= weight;
    }
}
