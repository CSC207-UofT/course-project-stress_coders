package entities;

public enum DefaultInteractableIDs {
    AXE("Axe"),
    ANIMAL("Animal"),
    CROSSBOW("Crossbow"),
    HANDCANNON("Hand cannon"),
    MAZE("Maze"),
    MYSTERYBOX("Mystery Box"),
    POTIONDISPENSER("Potion Dispenser"),
    RIDDLEGOBLIN("Riddle Goblin"),
    TREE("Tree"),
    POTION("Potion"),
    VAULTDOOR("Vault door"); // should be changed to , if JOUST used
    // JOUST("Joust");

    private final String defaultID;

    DefaultInteractableIDs(String defaultID){
        this.defaultID = defaultID;
    }

    public String getDefaultID() {
        return defaultID;
    }
}
