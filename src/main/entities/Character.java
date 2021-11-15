package entities;

/*
Generic character class for shared behaviour between living things.
I.e. NPCs, Enemies and the player.
 */
public abstract class Character extends Interactable {
    private int healthPoints;
    private int maxHealthPoints = 100;

    /**
     * Constructs a Character object
     *
     * @param id the id of character
     */
    public Character(String id) {
        super(id, "");
    }
    public Character(String id, String howTo) {
        super(id, howTo);
    }

    /**
     * set the health points of character
     *
     * @param healthPoints the health points of character
     */
    public void setHealthPoints(int healthPoints) {
        if (healthPoints <= maxHealthPoints) {
            this.healthPoints = healthPoints;
        }
        else {
            this.healthPoints = this.maxHealthPoints;
        }
    }

    public int getMaxHealthPoints(){
        return this.maxHealthPoints;
    }

    /**
     * set the max health points for character
     *
     * @param maxHP the max health points of character
     */
    public void setMaxHealthPoints(int maxHP) {
        this.maxHealthPoints = maxHP;
    }

    /**
     * get the health points of character
     *
     * @return int representing health points of character
     */
    public int getHealthPoints(){
        return this.healthPoints;
    }

    /**
     * Check whether character is dead.
     * If character's health points are less than or equal to 0.
     *
     * @return boolean whether character is dead
     */
    public boolean isDead() {return this.healthPoints <= 0;}

    /**
     * modify health points based on weight
     *
     * @param weight or damage that modifies health points
     */
    public void addModifier(float weight){
        this.healthPoints *= weight;
    }
}
