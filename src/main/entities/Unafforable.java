package entities;

import entities.weapons.Weapon;

// this class is just for typecasting for players that don't have money
public class Unafforable extends Weapon {
    public Unafforable() {
        super("broke", "not Used");
    }
}
