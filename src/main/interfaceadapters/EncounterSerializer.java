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
Take encounters and turn them into a string to be written to a save file so that they can be easily
and accurate recreated into the original encounter object
 */
public class EncounterSerializer {

    /*
    Instance variables of the encounter that are to be saved.
    The vars are stored in the format var1;var2;var3; etc. as strings. The more complex objs are stored
    as strings representing Json Objs indicated by the c.

    When you take the encounter serializations and split the string with ; to get an array of the variables
    (i.e var1;var2;var3;.split(";") = [var1, var2, var3] the value stored in the enum gives the index of
    the respective property based on the way the encounters are serialized.
     */
    private enum Components{

        NAME(0),
        DESCRIPTION(1),
        OBJ_IDS(2),         // c
        IS_COMPLETED(3),
        PROGRESSION(4),             //c
        CURR_INTERACTABLE_INDEX(5),
        INITIAL_TEXT(6),
        GENERIC_POOL(7),            // c
        DOING_GENERIC(8),
        CURR_GENERIC_INDEX(9);

        private final int index;

        Components(int index){
            this.index = index;
        }
    }

    // A Gson instance that can properly serialize interactables. A normal instance would not retain type information
    private final Gson interactableSerializer;

    public EncounterSerializer(){
        /*
        This is to store type information so that when the objects are deserialized they are deserialized into their
        proper types, otherwise they are just generic interactables (which would break teh game )
        due to the way they were defined, so they could be used polymorphically
         */
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
                .registerSubtype(PhoenixHatchling.class).registerSubtype(PotionDispenser.class).registerSubtype(Stone.class)
                .registerSubtype(Tree.class).registerSubtype(Enemy.class);

        interactableSerializer = new GsonBuilder().registerTypeAdapterFactory(interactableAdapterFactory).create();
    }

    /*
    Turn the encounter into a string to save to the files as per the format outlined above. The more complex objects
    have helper methods.
     */
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

    /*
    Using the Gson object with the type information save the interactables as Json.
    Obj IDs is a hash map of String ID : interactable.

    Save in the format:

    ID::interactable,,ID::interactable,, etc..
     */
    public String serializeObjIds(Encounter e){
        StringBuilder serialization = new StringBuilder();
        for (String key : e.getInteractablesManager().objIDs.keySet()){
            e.getInteractablesManager().objIDs.put(key, removePlayer(e.getInteractablesManager().objIDs.get(key)));
            serialization.append(key).append("::").append(interactableSerializer.toJson(e.getInteractablesManager().objIDs.get(key), Interactable.class)).append(",,");
        }
        /*
        An extra ,, is added to the end of the hashmap provided it was non-empty so remove that.
         */
        if(serialization.length() > 2) {
            return serialization.substring(0, serialization.length() - 2);
        }
        return serialization.toString();
    }


    /*
    Same as above, but this is an arraylist. Note that the arraylist contains objects
    that are aliases to the objects in Hashmap.

    Save in format:

    Interactable,,Interactable,, etc..
     */
    public String serializeArrayList(ArrayList<Interactable> interactables){
        StringBuilder serialization = new StringBuilder();
        for (Interactable interactable : interactables){
            interactable = removePlayer(interactable);
            serialization.append(interactableSerializer.toJson(interactable, Interactable.class)).append(",,");
        }
        /*
        an extra ,, is added if the arraylist is non-empty
         */
        if(serialization.length() > 2) {
            return serialization.substring(0, serialization.length() - 2);
        }
        return serialization.toString();
    }

    /*
    Take the saved string, that is in the format as described above and reconstruct the encounter.
    For the instance variables that are simple we can parse them from the string directly and type cast them if
    needed and assign a new encounter object with the respective vars.

    For the more complex variables we need a more thorough deserialization process. (The same complex variables outlined above)
     */
    public Encounter deserializeEncounter(Player player, String serialization){
        String[] components = serialization.split(";");

        Encounter encounter = new Encounter(components[Components.INITIAL_TEXT.index], components[Components.NAME.index],
                components[Components.DESCRIPTION.index]);

        encounter.setCompleted(Boolean.parseBoolean(components[Components.IS_COMPLETED.index]));
        encounter.setDoingGeneric(Boolean.parseBoolean(components[Components.DOING_GENERIC.index]));
        encounter.setCurrInteractableIndex(Integer.parseInt(components[Components.CURR_INTERACTABLE_INDEX.index]));
        encounter.setCurrGenericIndex(Integer.parseInt(components[Components.CURR_GENERIC_INDEX.index]));

        String serializedHashMap = components[Components.OBJ_IDS.index];
        encounter.getInteractablesManager().objIDs = deSerializeObjIds(serializedHashMap, player);

        String serializedProgression = components[Components.PROGRESSION.index];
        ArrayList<Interactable> progressionNonRef = deSerializeArrayList(serializedProgression, player);
        ArrayList<Interactable> progression = fixReferences(progressionNonRef, encounter.getInteractablesManager().objIDs);

        encounter.setProgression(progression);

        String serializedGenericPool = components[Components.GENERIC_POOL.index];
        ArrayList<Interactable> genericPoolNonRef = deSerializeArrayList(serializedGenericPool, player);
        ArrayList<Interactable> genericPool = fixReferences(genericPoolNonRef, encounter.getInteractablesManager().objIDs);

        encounter.setGenericPool(genericPool);


        return encounter;
    }

    /*
    This method is needed because progression and the generic pool should contain references of objects in objIDs. If
    we just save the objects directly and reconstruct them they will be identical copies but not references.

    Here we fix that.
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

    /*
    use the format we save the arraylist to get an array of Json and use the Gson deserializer with type information to
    get back the interactable of the correct type.

    Even though the arraylists contain only objects that are in the hashmap we do this, so we know what interactables
    the arraylists should contain and compare it with the objects in the hashmap to then correctly make them aliases.
     */
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

    /*
    Reconstruct the objIds hashmap using the format described above using the Gson deserializer.
     */
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
    Some objects need to store a reference to the player to be able to function correctly. Naturally,
    they all need to effect the same player, so they must reference the same player.

    IF we just save them and try to deserialize them with the same player object referenced on reconstruction they will
    all contain duplicate identical players.

    Here we take a player constructed earlier and make sure all of the objects reference the same player


    This is why player is passed as an argument to many of the encounter serializer methods, it was loaded
    before from teh gamestate serializer and then passed here so that it can be passed
    to this method to fix the reference
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

    /*
    As mentioned some objects reference the player character.

    We can't save the player correctly easily because the player contains a hashmap of items
    which need their own serializer with type information.

    If we wanted to save that correctly we would need another serializer instead of the one interactable
    serializer which can save everything else but the player correctly. We can safely remove the player
    from all of these objects and replace them with a empty player because we need to make these objects reference
    the same player anyways. For the reason outlined in the above method.

    So we can just save and load the player once in a simpler process. This is done in the gameState serializer class.
     */
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
