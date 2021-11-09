package entities;
import entities.*;
public abstract class Item extends Interactable {
    private Interactable heldBy = null;
    public Item(String id) {
        super(id);
    }

    public void setHeldBy(Interactable heldBy) {
        this.heldBy = heldBy;
    }
}
