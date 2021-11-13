package usecases;

import entities.*;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class SpinTest {

    @Test
    public void execute() {
        HashMap<String, Interactable> testArgs = new HashMap<>();
        Tree testTree = new Tree("id");
        testArgs.put("box", testTree);

        Spin spinCommand = new Spin();
        assertEquals("Invalid mystery box, please pass in a valid mystery box.", spinCommand.execute(testArgs));

        Player player = new Player("id");
        Axe testAxe = new Axe("axe1");
        Weapon[] weapons = {testAxe};
        MysteryBox mysteryBoxTest = new MysteryBox("I once knew a man named J calv", weapons, player);
        player.addCurrency(100);

        testArgs.put("box", mysteryBoxTest);
        assertEquals("Sorry, you cannot afford to hit the mystery box, come back later!", spinCommand.execute(testArgs));

        // Cannot test the rest since there is a scanner.
    }
}