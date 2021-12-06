package entities.interfaces;

import entities.Interactable;

public interface Target {
    /**
     * Depicts if something can get hit by a shootable
     * See ShootableWeapon
     */
    String shotAt(Interactable shootable);
}
