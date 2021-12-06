package interfaceadapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.*;
import entities.characters.*;
import entities.food.*;
import entities.minigames.HorseRace;
import entities.minigames.Joust;
import entities.minigames.Maze;
import entities.minigames.VaultDoor;
import entities.weapons.*;
import usecases.Encounter;

import java.util.ArrayList;
import java.util.HashMap;

/*
This class violates CA, but its a must for now.
 */
public class EncounterSerializer {

    private enum Components{

        NAME(0),
        DESCRIPTION(1),
        OBJ_IDS(2),
        IS_COMPLETED(3),
        PROGRESSION(4),
        CURR_INTERACTABLE_INDEX(5),
        INITIAL_TEXT(6),
        GENERIC_POOL(7),
        DOING_GENERIC(8),
        CURR_GENERIC_INDEX(9);

        private int index;

        Components(int index){
            this.index = index;
        }
    }

    private final Gson interactableSerializer;

    public EncounterSerializer(){
        RuntimeTypeAdapterFactory<Interactable> interactableAdapterFactory = RuntimeTypeAdapterFactory.of(Interactable.class)
                .registerSubtype(VaultDoor.class).registerSubtype(RiddleGoblin.class).registerSubtype(MysteryBox.class)
                .registerSubtype(Animal.class).registerSubtype(Dragon.class).registerSubtype(FeralGhoul.class)
                .registerSubtype(Goblin.class).registerSubtype(MadAcrobat.class).registerSubtype(Ork.class)
                .registerSubtype(Player.class).registerSubtype(Trader.class).registerSubtype(Berries.class)
                .registerSubtype(Meat.class).registerSubtype(Nuts.class).registerSubtype(Potion.class)
                .registerSubtype(Potato.class).registerSubtype(RefillablePotion.class).registerSubtype(SuspiciousMushroom.class)
                .registerSubtype(UnusablePotion.class).registerSubtype(Axe.class).registerSubtype(Crossbow.class)
                .registerSubtype(ElvenSharpshooter.class).registerSubtype(HandCannon.class).registerSubtype(Slingshot.class)
                .registerSubtype(Spear.class).registerSubtype(ThrowingKnife.class).registerSubtype(HauntedArmor.class)
                .registerSubtype(HorseRace.class).registerSubtype(Joust.class).registerSubtype(Maze.class)
                .registerSubtype(PheonixHatchling.class).registerSubtype(PotionDispenser.class).registerSubtype(Stone.class)
                .registerSubtype(Tree.class).registerSubtype(Enemy.class);

        interactableSerializer = new GsonBuilder().registerTypeAdapterFactory(interactableAdapterFactory).create();
    }

    public String serializeEncounter(Encounter e){
        String serialization = "";
        serialization += e.getName() + ";";
        serialization += e.getDescription() + ";";
        serialization += serializeObjIds(e) + ";";
        serialization += e.isCompleted() + ";";
        serialization += serializeArrayList(e.getProgression()) + ";";
        serialization += e.getCurrInteractableIndex() + ";";
        serialization += e.getInitialText() + ";";
        serialization += serializeArrayList(e.getGenericPool()) + ";";
        serialization += e.isDoingGeneric() + ";";
        serialization += e.getCurrGenericIndex();

        return serialization;
    }

    public String serializeObjIds(Encounter e){
        StringBuilder serialization = new StringBuilder();
        for (String key : e.objIDs.keySet()){
            e.objIDs.put(key, removePlayer(e.objIDs.get(key)));
            serialization.append(key).append("::").append(interactableSerializer.toJson(e.objIDs.get(key), Interactable.class)).append(",,");
        }
        if(serialization.length() > 2) {
            return serialization.substring(0, serialization.length() - 2);
        }
        return serialization.toString();
    }

    public String serializeArrayList(ArrayList<Interactable> interactables){
        StringBuilder serialization = new StringBuilder();
        for (Interactable interactable : interactables){
            interactable = removePlayer(interactable);
            serialization.append(interactableSerializer.toJson(interactable, Interactable.class)).append(",,");
        }
        if(serialization.length() > 2) {
            return serialization.substring(0, serialization.length() - 2);
        }
        return serialization.toString();
    }

    public Encounter deserializeEncounter(Player player, String serialization){
        String[] components = serialization.split(";");

        Encounter encounter = new Encounter(components[Components.INITIAL_TEXT.index], components[Components.NAME.index],
                components[Components.DESCRIPTION.index]);

        encounter.setCompleted(Boolean.parseBoolean(components[Components.IS_COMPLETED.index]));
        encounter.setDoingGeneric(Boolean.parseBoolean(components[Components.DOING_GENERIC.index]));
        encounter.setCurrInteractableIndex(Integer.parseInt(components[Components.CURR_INTERACTABLE_INDEX.index]));
        encounter.setCurrGenericIndex(Integer.parseInt(components[Components.CURR_GENERIC_INDEX.index]));

        String serializedHashMap = components[Components.OBJ_IDS.index];
        encounter.objIDs = deSerializeObjIds(serializedHashMap, player);

        String serializedProgression = components[Components.PROGRESSION.index];
        ArrayList<Interactable> progressionNonRef = deSerializeArrayList(serializedProgression, player);
        ArrayList<Interactable> progression = fixReferences(progressionNonRef, encounter.objIDs);

        encounter.setProgression(progression);

        String serializedGenericPool = components[Components.GENERIC_POOL.index];
        ArrayList<Interactable> genericPoolNonRef = deSerializeArrayList(serializedGenericPool, player);
        ArrayList<Interactable> genericPool = fixReferences(genericPoolNonRef, encounter.objIDs);

        encounter.setGenericPool(genericPool);


        return encounter;
    }

    /*
    objIDs and progression/generic pool should reference the same objects, not different objects.
     */
    public ArrayList<Interactable> fixReferences(ArrayList<Interactable> arrayList, HashMap<String, Interactable> objIds){
        ArrayList<Interactable> fixedReferences = new ArrayList<>();

        for(Interactable interactable : arrayList){
            for(Interactable match : objIds.values()){
                if (interactable.equals(match)){
                    fixedReferences.add(match);
                }
            }
        }
        return fixedReferences;
    }

    public ArrayList<Interactable> deSerializeArrayList(String serialization, Player p){
        String[] interactables = serialization.split(",,");
        ArrayList<Interactable> arrayList = new ArrayList<>();
        for(String interactable : interactables){
            Interactable deSerializedInteractable = interactableSerializer.fromJson(interactable, Interactable.class);
            deSerializedInteractable = standardizePlayer(deSerializedInteractable, p);

            arrayList.add(deSerializedInteractable);
        }
        return arrayList;
    }

    public HashMap<String, Interactable> deSerializeObjIds(String serialization, Player p){
        HashMap<String, Interactable> objIds = new HashMap<>();
        String[] keyValuePairs = serialization.split(",,");

        for(String pair : keyValuePairs){
            String[] splitPairs = pair.split("::");
            Interactable deSerializedInteractable = interactableSerializer.fromJson(splitPairs[1], Interactable.class);

            deSerializedInteractable = standardizePlayer(deSerializedInteractable, p);
            objIds.put(splitPairs[0], deSerializedInteractable);
        }

        return objIds;
    }

    /*
    All of these objects must reference the same player.
     */
    public Interactable standardizePlayer(Interactable deSerializedInteractable, Player p){
        if(deSerializedInteractable instanceof Enemy){
            ((Enemy) deSerializedInteractable).player = p;
        } else if(deSerializedInteractable instanceof HorseRace){
            ((HorseRace) deSerializedInteractable).setPlayer(p);
        } else if(deSerializedInteractable instanceof MysteryBox){
            ((MysteryBox) deSerializedInteractable).setPlayer(p);
        } else if(deSerializedInteractable instanceof Maze){
            ((Maze) deSerializedInteractable).setPlayer(p);
        } else if(deSerializedInteractable instanceof Joust){
            ((Joust) deSerializedInteractable).setPlayer(p);
        } else if(deSerializedInteractable instanceof PotionDispenser){
            ((PotionDispenser) deSerializedInteractable).setPlayer(p);
        } else if(deSerializedInteractable instanceof Trader) {
            ((Trader) deSerializedInteractable).setPlayer(p);
        } else if(deSerializedInteractable instanceof RiddleGoblin) {
            ((RiddleGoblin) deSerializedInteractable).setPlayer(p);
        } else if(deSerializedInteractable instanceof Animal) {
            ((Animal) deSerializedInteractable).setPlayer(p);
        }
        return deSerializedInteractable;
    }

    public Interactable removePlayer(Interactable interactable){
        Player p = new Player();
        p.setItems(new HashMap<>());
        if(interactable instanceof Enemy){
            ((Enemy) interactable).player = p;
        } else if(interactable instanceof HorseRace){
            ((HorseRace) interactable).setPlayer(p);
        } else if(interactable instanceof MysteryBox){
            ((MysteryBox) interactable).setPlayer(p);
        } else if(interactable instanceof Maze){
            ((Maze) interactable).setPlayer(p);
        } else if(interactable instanceof Joust){
            ((Joust) interactable).setPlayer(p);
        } else if(interactable instanceof PotionDispenser){
            ((PotionDispenser) interactable).setPlayer(p);
        } else if(interactable instanceof Trader) {
            ((Trader) interactable).setPlayer(p);
        } else if(interactable instanceof RiddleGoblin) {
            ((RiddleGoblin) interactable).setPlayer(p);
        } else if(interactable instanceof Animal) {
            ((Animal) interactable).setPlayer(p);
        }
        return interactable;
    }
}
