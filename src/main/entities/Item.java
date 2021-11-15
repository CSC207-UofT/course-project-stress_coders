package entities;

/**
Represents an interactable that can be held by a Character. Can be added to the players inventory
but does not need to be.

I.e:
An axe is an item, a button is not.
 */
public abstract class Item extends Interactable {
    // The character holding this object. (Change the type heading for this later)
    private Interactable heldBy = null;
    public Item(String id, String howToUse) {
        super(id, howToUse);
    }

    public void setHeldBy(Interactable heldBy) {
        this.heldBy = heldBy;
    }
    public Interactable getHeldBy() {
        return this.heldBy;
    }
}
