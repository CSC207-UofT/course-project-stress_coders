
import entities.Player;
import org.junit.Test;
import usecases.PlayerManager;

import static org.junit.Assert.assertEquals;

public class PlayerManagerTest {

    @Test
    public void getPlayer() {
        PlayerManager playerManager = new PlayerManager("id", "easy");
        assertEquals("id", playerManager.getPlayer().getId());
    }
}