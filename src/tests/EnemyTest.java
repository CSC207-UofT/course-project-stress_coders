import entities.Axe;
import entities.Enemy;
import entities.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class EnemyTest {

    @Test
    public void handleHit() {
        Enemy testEnemy = new Enemy("enemy", new Player("id"), 1);
        testEnemy.setHealthPoints(10);
        Axe testAxe = new Axe("axe");

        assertEquals("Your axe hits enemy for 20 damage! You killed the beast!", testEnemy.handleHit(testAxe));

        testEnemy.setHealthPoints(100);

        assertTrue(testEnemy.handleHit(testAxe).contains("Your axe hits enemy for 20 damage!"));

    }

    @Test
    public void getPlayer() {
        Player playerTest = new Player("id");
        Enemy enemyTest = new Enemy("id2", playerTest, 1);

        assertEquals(playerTest, enemyTest.getPlayer());
    }
}