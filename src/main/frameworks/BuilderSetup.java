package frameworks;

import entities.*;
import entities.Interactable;
import entities.Player;
import usecases.Encounter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class BuilderSetup {
    List<Encounter> allGeneratedEncounters;
    List<Interactable> allPossibleInteractables = new ArrayList<>(); // WILL ADD userGenre input later
    List<Interactable> allGenerics = new ArrayList<>();
    List<Interactable> allMain = new ArrayList<>();
    HashMap<String, Integer> usedEncounterTypes= new HashMap<>();
    HashMap<String, String[]> encounterTypeDetails = new HashMap<>(); // Each value is stored as a list of
    // {description, initial_text}
    Player player;
    String userGenre;
    int questLengthBound;


    // First we create an instance of each interactable, possibly set certain ones by genre, then from valid ones
    // randomly choose some premade MAIN interactions and some generics using randint and asking user to choose from
    // quests of length (short, medium, long) which affects the bound

    public BuilderSetup(Player player, String questLength) {
        this.player = player;
        this.allPossibleInteractables = new ArrayList<>();
        this.allGeneratedEncounters = new ArrayList<>();
        if (questLength.equals("short")) {
            this.questLengthBound = 5;
        }
        else if (questLength.equals("medium")) {
            this.questLengthBound = 10;
        }
        else {
            this.questLengthBound = 15;
        }
        loadEncounterGenerator();
    }

    public void loadEncounterGenerator() {
        usedEncounterTypes.put("Survival", 0);
        usedEncounterTypes.put("Hidden Cave", 0);
        usedEncounterTypes.put("Castle", 0);
        usedEncounterTypes.put("Fetch Item", 0);
        usedEncounterTypes.put("Heroic", 0);
        encounterTypeDetails.put("Survival", new String[]{"Your goal in this encounter is to survive!",
                "Good luck beating this one chief!"});
        encounterTypeDetails.put("Hidden Cave", new String[]{"Your goal is to make it through this mysterious cave!",
                "Expect riddles, good luck!"});
        encounterTypeDetails.put("Castle", new String[]{"Your goal is to clear this castle!",
                "Save the city!"});
        encounterTypeDetails.put("Fetch Item", new String[]{"Your goal is to retrieve the item!",
                "Don't expect it to be so easy!"});
        encounterTypeDetails.put("Heroic", new String[]{"Your goal is save the world!",
                "Show them your true colors!"});
    }

    public List<Encounter> build() {
        buildInteractables();
        for (int i = 0; i <= questLengthBound; i++) {
            buildEncounter();
        }
        return this.allGeneratedEncounters;
    }

    public void buildEncounter() {
        // We should add a smart generator here for type of encounters, will add after we make sure it all works
        Encounter e = encounterDetailGenerator();
        for (int i = 0; i<= questLengthBound; i++) {
            Random r = new Random();
            Interactable mainChoice = allMain.get(r.nextInt(allMain.size()));
            Interactable genericChoice = allGenerics.get(r.nextInt(allGenerics.size()));
            e.addGeneric(genericChoice);
            e.addObj(mainChoice);
        }
        allGeneratedEncounters.add(e);
    }

    public void buildInteractables() {
        Random r = new Random();
        this.allPossibleInteractables.add(new Axe(DefaultInteractableIDs.AXE.name()));
        this.allPossibleInteractables.add(new Animal(DefaultInteractableIDs.ANIMAL.name(), r.nextInt(100),player));
        this.allPossibleInteractables.add(new Crossbow(DefaultInteractableIDs.CROSSBOW.name(), r.nextInt(30)));
        this.allPossibleInteractables.add(new HandCannon(DefaultInteractableIDs.HANDCANNON.name(), r.nextInt(30)));
        this.allPossibleInteractables.add(new Potion(DefaultInteractableIDs.POTION.name()));
        this.allPossibleInteractables.add(new Tree(DefaultInteractableIDs.TREE.name()));
        VaultDoor v = generateVaultDoor();
        MysteryBox m = generateMysteryBox();
        PotionDispenser p = generatePotionDispenser();
        RiddleGoblin riddleGoblin = new RiddleGoblin("riddler", player, 10);
        Enemy enemy = new Enemy("Random Enemy", player, 25); // Should generate enemy names
        this.allPossibleInteractables.add(v);
        this.allPossibleInteractables.add(m);
        this.allPossibleInteractables.add(p);
        this.allPossibleInteractables.add(enemy);

        allMain.add(v);
        allMain.add(riddleGoblin);
        allMain.add(enemy);

        for (Interactable i: allPossibleInteractables) {
            if (!allMain.contains(i)) {
                allGenerics.add(i);
            }
        }
        Potion healthPotion = new Potion("health potion", 20);
        Berries blueberry = new Berries("blueberry");
        Nuts peanut = new Nuts("peanut");
        Meat cookedBeef = new Meat("cooked beef");
        for (int i = 0; i <= 5; i++) {
            player.addConsumable(healthPotion);
            player.addConsumable(blueberry);
            player.addConsumable(peanut);
            player.addConsumable(cookedBeef);
        }
        Axe a = new Axe("Big Boi");
        player.setWeapon(a);
    }

    public VaultDoor generateVaultDoor() {
        Random r = new Random();
        List<String> hints = new ArrayList<>();
        StringBuilder generated = new StringBuilder();
        for (int i = 0; i <= 6; i++) {
            int x = r.nextInt(9);
            generated.append(((Integer) x).toString());
            hints.add("The " + i + " slot of this password is " + x);
        }
        return new VaultDoor(DefaultInteractableIDs.VAULTDOOR.name(), hints.toArray(new String[0]), generated.toString());
    }

    public MysteryBox generateMysteryBox() {
        List<Weapon> weapons = new ArrayList<>();
        for (Interactable i : this.allPossibleInteractables) {
            if (i instanceof Weapon) {
                weapons.add((Weapon) i);
            }
        }
        return new MysteryBox(DefaultInteractableIDs.MYSTERYBOX.name(), weapons, player);
    }

    public PotionDispenser generatePotionDispenser() {
        List<Potion> potions = new ArrayList<>();
        for (Interactable i : this.allPossibleInteractables) {
            if (i instanceof Potion) {
                potions.add((Potion) i);
            }
        }
        return new PotionDispenser(DefaultInteractableIDs.POTIONDISPENSER.name(), potions.toArray(new Potion[0]), player);
    }

    public Encounter encounterDetailGenerator() {
        Random r = new Random();

        int choice = r.nextInt(usedEncounterTypes.size());
        String type = usedEncounterTypes.keySet().toArray(new String[0])[choice];
        String name = type + " "+ (usedEncounterTypes.get(type)+1);
        String description = getTypeDescription(type);
        String initial = getTypeInitial(type);
        usedEncounterTypes.put(type, usedEncounterTypes.get(type)+1);
        return new Encounter(initial, name, description);
    }

    public String getTypeDescription(String type) {
        return encounterTypeDetails.get(type)[0];
    }

    public String getTypeInitial(String type) {
        return encounterTypeDetails.get(type)[1];
    }
}
