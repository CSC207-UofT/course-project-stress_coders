
import entities.weapons.Axe;
import entities.characters.Enemy;
import entities.Interactable;
import entities.characters.Player;
import org.junit.Test;
import interfaceadapters.commands.ThrowCommand;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class ThrowTest {

    @Test
    public void execute() {
        Player p = new Player("Deez Nuts");
        Enemy testEnemy = new Enemy("enemy", p, 1);
        testEnemy.setHealthPoints(10);
        Axe testAxe = new Axe("axe");
        p.setWeapon(testAxe);
        ThrowCommand throwCommand = new ThrowCommand();
        HashMap<String, Interactable> args = new HashMap<>();
        args.put("throw_obj", testAxe);
        args.put("target", testEnemy);

        List<String> options = List.of(new String[]{"Your axe hits enemy for 20 damage! You killed the beast!", "It missed"});
        String res = throwCommand.execute(args);
        assertTrue(options.contains(res));
    }
}