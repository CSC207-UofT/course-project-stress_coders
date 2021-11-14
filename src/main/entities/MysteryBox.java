package entities;

import entities.interfaces.Spinnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MysteryBox extends Interactable implements Spinnable {

    public List<Weapon> weapons;
    Player player;

    public MysteryBox(String id, Weapon[] weapons, Player p) {
        super(id, "spin: box=[mysterybox_id]}");
        this.weapons = new ArrayList<>();
        loadWeapons(weapons);
        player = p;
    }
    public void loadWeapons(Weapon[] weapons) {
        this.weapons.addAll(List.of(weapons));
    }

    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
    }

    public Weapon spin() {
        if (player.getWallet() < 950) {
            return new Unafforable();
        }
        player.subCurrency(950);
        Random r = new Random();
        int chosen = r.nextInt(this.weapons.size() + 1);
        return weapons.get(chosen);
    }

    public void changePlayerWeapon(Weapon w) {
        player.setWeapon(w);
    }
}
