package entities;

public enum DefaultInteractableIDs {
    AXE("Axe"),
    ENEMY("Enemy");

    private final String defaultID;

    DefaultInteractableIDs(String defaultID){
        this.defaultID = defaultID;
    }
}