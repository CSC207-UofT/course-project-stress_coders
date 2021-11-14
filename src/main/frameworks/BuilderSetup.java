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
    List<Interactable> allPossibleInteractables; // WILL ADD userGenre input later
    List<Interactable> allGenerics;
    List<Interactable> allMain;
    Player player;
    String userGenre;
    int questLengthBound;


    // First we create an instance of each interactable, possibly set certain ones by genre, then from valid ones
    // randomly choose some premade MAIN interactions and some generics using randint and asking user to choose from
    // quests of length (short, medium, long) which affects the bound

    public BuilderSetup(Player player, String userGenreSelection, String questLength) {
        this.player = player;
        this.userGenre = userGenreSelection;
        this.allPossibleInteractables = new ArrayList<>();
        if (questLength.equals("short")) {
            this.questLengthBound = 5;
        }
        else if (questLength.equals("medium")) {
            this.questLengthBound = 10;
        }
        else {
            this.questLengthBound = 15;
        }
    }

    public void buildEncounters() {
        String TO_CHANGE = "Quest " + allGeneratedEncounters.size();
        String TO_CHANGE_DESC = "JUST TO DEMO";
        String TO_CHANGE_INITIAL = "BLAH";
        Encounter e = new Encounter( TO_CHANGE_INITIAL, TO_CHANGE, TO_CHANGE_DESC);
        for (int i = 0; i<= questLengthBound; i++) {
            Random r = new Random();
            Interactable mainChoice = allMain.get(r.nextInt(allMain.size()+1));
            Interactable genericChoice = allGenerics.get(r.nextInt(allGenerics.size()+1));
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
        this.allPossibleInteractables.add(generateVaultDoor());
        this.allPossibleInteractables.add(generateMysteryBox());
        this.allPossibleInteractables.add(generatePotionDispenser());
        // ADD TO GENERICS AND ADD TO MAIN DEPENDING ON TYPE TO DO TMRW
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
}
