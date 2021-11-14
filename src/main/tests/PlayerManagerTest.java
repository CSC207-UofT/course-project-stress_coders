package tests;

import entities.Player;
import org.junit.Test;
import usecases.PlayerManager;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class PlayerManagerTest {

    @Test
    public void getPlayer() {
        Player testPlayer = new Player("id");
        PlayerManager playerManager = new PlayerManager("id");
        assertEquals(testPlayer, playerManager.getPlayer());
    }
}