package entities;

public enum DefaultInteractableIDs {
    AXE("Axe"),
    ANIMAL("Animal"),
    CROSSBOW("Crossbow"),
    DOOR("Door"),
    HANDCANNON("Hand cannon"),
    MYSTERYBOX("Mystery Box"),
    POTIONDISPENSER("Potion Dispenser"),
    RIDDLEGOBLIN("Riddle Goblin"),
    TREE("Tree"),
    POTION("Potion"),
    VAULTDOOR("Vault door");



    private final String defaultID;

    DefaultInteractableIDs(String defaultID){
        this.defaultID = defaultID;
    }

    public String getDefaultID() {
        return defaultID;
    }
}
