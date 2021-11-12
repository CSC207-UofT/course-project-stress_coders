package entities;

import entities.interfaces.Spinnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PotionDispenser extends Interactable implements Spinnable {
    List<Potion> potions;
    Player player;

    public PotionDispenser(String id, Potion[] potions, Player p) {
        super(id, "spin: box=[mysterybox_id]}");
        this.potions = new ArrayList<>();
        loadPotions(potions);
        player = p;
    }
    public void loadPotions(Potion[] potions) {
        this.potions.addAll(List.of(potions));
    }

    public void addPotion(Potion potion) {
        potions.add(potion);
    }

    public Potion spin() {
        if (player.getWallet() < 950) {
            return new UnusablePotion();
        }
        player.subCurrency(950);
        Random r = new Random();
        int chosen = r.nextInt(this.potions.size() + 1);
        return potions.get(chosen);
    }
}

