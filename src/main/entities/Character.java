package entities;

/*
Generic character class for shared behaviour between living things.
I.e. NPCs, Enemies and the player.
 */
public abstract class Character extends Interactable {
    private int healthPoints;

    public Character(String id) {
        super(id);
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getHealthPoints(){
        return this.healthPoints;
    }

    public boolean isDead() {return this.healthPoints <= 0;}
}
