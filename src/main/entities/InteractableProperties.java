package entities;

/**
 * Store all possible parameters that commands can use
 * @see Variable
 * For how these parameters are used
 */
public enum InteractableProperties {
    HEALTH_RESTORED("hp"),
    HIT_PROB("hit_prob"),
    WEIGHT("weight");

    private final String name;

    InteractableProperties(String s){
        this.name = s;
    }
}
