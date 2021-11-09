package entities;
import entities.*;

public enum InteractableProperties {
    HEALTH_RESTORED("hp"),
    HIT_PROB("hit_prob"),
    WEIGHT("weight");

    private final String name;

    InteractableProperties(String s){
        this.name = s;
    }
}
