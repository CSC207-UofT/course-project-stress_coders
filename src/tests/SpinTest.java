
import entities.*;
import entities.characters.Player;
import entities.minigames.MysteryBox;
import entities.weapons.Axe;
import entities.weapons.Weapon;
import org.junit.Test;
import usecases.Spin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SpinTest {

    @Test
    public void execute() {
        HashMap<String, Interactable> testArgs = new HashMap<>();
        Tree testTree = new Tree("id");
        testArgs.put("box", testTree);

        Spin spinCommand = new Spin();
        assertEquals("Invalid box, please pass in a valid box.", spinCommand.execute(testArgs));

        Player player = new Player("id");
        Axe testAxe = new Axe("axe1");
        List<Weapon> weapons = new ArrayList<>();
        weapons.add(testAxe);
        MysteryBox mysteryBoxTest = new MysteryBox("I once knew a man named J calv", weapons, player);
        player.addCurrency(100);

        testArgs.put("box", mysteryBoxTest);
        assertEquals("Sorry, you cannot afford to hit the mystery box, come back later!", spinCommand.execute(testArgs));

        // Cannot test the rest since there is a scanner.
    }
}