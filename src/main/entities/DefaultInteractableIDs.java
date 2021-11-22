package entities;

public enum DefaultInteractableIDs {
    AXE("Axe"),
    ANIMAL("Animal"),
    CROSSBOW("Crossbow"),
    HANDCANNON("Hand cannon"),
    MYSTERYBOX("Mystery Box"),
    POTIONDISPENSER("Potion Dispenser"),
    RIDDLEGOBLIN("Riddle Goblin.txt"),
    TREE("Tree"),
    POTION("Potion"),
    VAULTDOOR("Vault door"),
    JOUST("Joust");

    private final String defaultID;

    DefaultInteractableIDs(String defaultID){
        this.defaultID = defaultID;
    }

    public String getDefaultID() {
        return defaultID;
    }
}
