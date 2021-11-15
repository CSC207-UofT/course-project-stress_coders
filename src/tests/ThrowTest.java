
import entities.Axe;
import entities.Enemy;
import entities.Interactable;
import entities.Player;
import org.junit.Test;
import usecases.Throw;

import java.util.HashMap;

import static org.junit.Assert.*;

public class ThrowTest {

    @Test
    public void execute() {
        Enemy testEnemy = new Enemy("enemy", new Player("id"), 1);
        testEnemy.setHealthPoints(10);
        Axe testAxe = new Axe("axe");
        Throw throwCommand = new Throw();
        HashMap<String, Interactable> args = new HashMap<>();
        args.put("thrown_obj", testAxe);
        args.put("target", testEnemy);

        assertEquals("Your axe hits enemy for 20 damage! You killed the beast!", throwCommand.execute(args));

        testEnemy.setHealthPoints(100);

        assertTrue(throwCommand.execute(args).contains("Your axe hits enemy for 20 damage!") || throwCommand.execute(args).contains("It missed"));
    }
}