package entities;

import entities.characters.Player;
import entities.interfaces.Spinnable;
import entities.weapons.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MysteryBox extends Interactable implements Spinnable {

    public List<Weapon> weapons;
    Player player;

    public MysteryBox(String id, List<Weapon> weapons, Player p) {
        super(id, "spin: box=[mysterybox_id]");
        this.weapons = new ArrayList<>();
        loadWeapons(weapons);
        player = p;
    }
    public void loadWeapons(List<Weapon> weapons) {
        this.weapons.addAll(weapons);
    }

    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
    }

    public Weapon spin() {
        if (player.getWallet() < 950) {
            this.setCompleted(true);
            return new Unaffordable();
        }
        player.subCurrency(950);
        Random r = new Random();
        int chosen = r.nextInt(this.weapons.size());
        this.setCompleted(true);
        return weapons.get(chosen);
    }

    public void changePlayerWeapon(Weapon w) {
        player.setWeapon(w);
    }

    public MysteryBox(){}

    public void setPlayer(Player p) {
        this.player = p;
    }
}
