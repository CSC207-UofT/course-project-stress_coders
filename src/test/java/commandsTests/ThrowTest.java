package commandsTests;

import commands.Throw;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import characters.Player;
import weapons.Axe;

public class ThrowTest{

    @Test
    public void testThrowAxeSuccess(){
        Axe accurate_axe = new Axe("Accurate Axe", 100);
        Player player_hit = new Player(null, 100, accurate_axe);
        Throw axe_throw_success = new Throw("Accurate Axe", "Throw", player_hit);

        String expected = "You hit your target";
        String actual =  axe_throw_success.getOutput();
        assertEquals(expected, actual);
    }

    @Test
    public void testThrowAxeFailure(){
        Axe missing_axe = new Axe("Missing Axe", 0);
        Player player_miss = new Player(null, 100, missing_axe);
        Throw axe_throw_failure = new Throw("Missing Axe", "Throw", player_miss);

        String expected = "You missed";
        String actual = axe_throw_failure.getOutput();
        assertEquals(expected, actual);
    }

    @Test
    public void testThrowAxeAgain(){
        Player player_none = new Player();
        Throw axe_throw_none = new Throw("No Axe", "Throw", player_none);
        String expected = "No weapon to throw";
        String actual = axe_throw_none.getOutput();
        assertEquals(expected, actual);
    }


}