package interfaceadapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entities.Item;
import entities.characters.Player;
import entities.food.*;
import entities.weapons.*;

import java.util.HashMap;

/*
Serialize and deserialize the Gamestate class.

Save the 3 important instance variables
 */
public class GamestateSerializer {

    final Gson serializer = new Gson();
    final Gson itemSerializer;
    final Gson weaponSerializer;

    /*
    Instance variables of the player and the index they correspond to based on the way the player is stored.

    See the encounter serializer class for more info on something similar.
     */
    private enum PlayerComponents{
        PLAYER_ID(0),
        HP(1),
        MAX_HP(2),
        INVENTORY(3),
        ITEMS(4),
        WALLET(5),
        WEAPON(6);

        private final int index;

        PlayerComponents(int index){
            this.index = index;
        }
    }

    public GamestateSerializer(){
        /**
        This is to correctly save the players items. This serves the same purpose as the interactable serializer
        in the ecnounterSerializer so check there for more info.
         @see EncounterSerializer
         **/
        RuntimeTypeAdapterFactory<Item> itemRuntimeTypeAdapterFactory = RuntimeTypeAdapterFactory.of(Item.class)
                .registerSubtype(Weapon.class).registerSubtype(Axe.class).registerSubtype(Crossbow.class).registerSubtype(HandCannon.class)
                .registerSubtype(Slingshot.class).registerSubtype(Spear.class).registerSubtype(ThrowingKnife.class).registerSubtype(Berries.class)
                .registerSubtype(Meat.class).registerSubtype(Nuts.class).registerSubtype(Potion.class)
                .registerSubtype(Potato.class).registerSubtype(RefillablePotion.class).registerSubtype(SuspiciousMushroom.class)
                .registerSubtype(UnusablePotion.class);

        /*
        Same thing but with weapons.
         */
        RuntimeTypeAdapterFactory<Weapon> weaponRuntimeTypeAdapterFactory = RuntimeTypeAdapterFactory.of(Weapon.class).registerSubtype(Axe.class).registerSubtype(Crossbow.class).registerSubtype(HandCannon.class)
                .registerSubtype(Slingshot.class).registerSubtype(Spear.class).registerSubtype(ThrowingKnife.class);

        itemSerializer = new GsonBuilder().registerTypeAdapterFactory(itemRuntimeTypeAdapterFactory).create();
        weaponSerializer = new GsonBuilder().registerTypeAdapterFactory(weaponRuntimeTypeAdapterFactory).create();
    }

    /**
    Same idea as deserializeEncounter
     @see EncounterSerializer
     **/
    public Player deserializePlayer(String serialization){
        String[] components = serialization.split(";");

        Player p = new Player();
        p.setId(components[PlayerComponents.PLAYER_ID.index]);
        p.setHealthPoints(Integer.parseInt(components[PlayerComponents.HP.index]));
        p.setMaxHealthPoints(Integer.parseInt(components[PlayerComponents.MAX_HP.index]));

        p.setInventory(serializer.fromJson(components[PlayerComponents.INVENTORY.index], new TypeToken<HashMap<String, Integer>>(){}.getType()));
        p.setWallet(Integer.parseInt(components[PlayerComponents.WALLET.index]));
        p.setWeapon(weaponSerializer.fromJson(components[PlayerComponents.WEAPON.index], Weapon.class));
        p.setItems(deserializeItems(components[PlayerComponents.ITEMS.index]));

        for(Item item : p.getItems().keySet()){
            item.setHeldBy(p);
        }
        return p;
    }

    /**
    Same idea as serializeEncounter
    @see EncounterSerializer
    **/
    public String serializePlayer(Player p){
        String serialization = "";

        serialization += p.getId() + ";";
        serialization += p.getHealthPoints() + ";";
        serialization += p.getMaxHealthPoints() + ";";
        serialization += serializer.toJson(p.getInventory(), new TypeToken<HashMap<String, Integer>>(){}.getType()) + ";";
        serialization += serializeItems(p.getItems()) + ";";
        serialization += p.getWallet() + ";";
        serialization += weaponSerializer.toJson(p.getCurrentWeapon(), Weapon.class);

        return serialization;
    }

    /**
    Same idea as deserializeObjIds,
     @see EncounterSerializer
    **/
    public HashMap<Item, Integer> deserializeItems(String items){
        String[] keyValuePairs = items.split(",,");
        HashMap<Item, Integer> itemsMap = new HashMap<>();

        for(String pair : keyValuePairs){
            String[] splitPair = pair.split("::");

            Item item = itemSerializer.fromJson(splitPair[0], Item.class);
            itemsMap.put(item, Integer.parseInt(splitPair[1]));
        }

        return itemsMap;
    }

    /**
     Same idea as serializeObjIds,
     @see EncounterSerializer
     **/
    public String serializeItems(HashMap<Item, Integer> items){
        StringBuilder serialization = new StringBuilder();

        for(Item item : items.keySet()){
            serialization.append(itemSerializer.toJson(item, Item.class)).append("::").append(items.get(item)).append(",,");
        }

        return serialization.substring(0, serialization.length() - 2);
    }
}
