package entities;

/**
 * Store all possible parameters that commands can use
 * @see Variable
 * For how these parameters are used
 */
public enum InteractableProperties {
    HEALTH_RESTORED("hp"),
    HIT_PROB("hit_prob"),
    WEIGHT("weight"),
    RES_NAME("resource_name"),
    RES_STORE_NAME("storage"),
    CHOP_DMG_NAME("chop_damage"),
    CONSUMABLE_REST_NAME("restoration_value");


    private final String name;

    InteractableProperties(String s){
        this.name = s;
    }
}
