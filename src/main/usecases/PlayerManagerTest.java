package usecases;

import entities.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerManagerTest {

    @Test
    public void getPlayer() {
        Player testPlayer = new Player("id");
        PlayerManager playerManager = new PlayerManager("id");
        assertEquals(testPlayer, playerManager.getPlayer());
    }
}