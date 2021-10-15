
import commands;
import org.junit.Test;
import org.junit.Before;
import characters.Player;
import playeritems.PlayerItems;
import weapons.Axe;

public class ThrowTest(){

    @Before
    public void testSetUp(){
        Axe accurate_axe = new Axe("Accurate Axe", 1);
        Axe missing_axe = new Axe("Missing Axe", 0);
        PlayerItems inventory = new PlayerItems();
        Player player_hit = new Player(null, 100, accurate_axe);
        Player player_miss = new Player(null, 100, missing_axe);
        Throw axe_throw_success = Throw("Accurate Axe", "Throw", player_hit);
        Throw axe_throw_failure = Throw("Missing Axe", "Throw", player_miss);
    }

    @Test
    @DisplayName("Test throwing the successful axe")
    public Boolean testThrowAxeSuccess(){
        expected = "You hit your target";
        actual = ((Action) axe_throw_success).getOutput();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test throwing the failing axe")
    public Boolean testThrowAxeSuccess(){
        expected = "You missed";
        actual = ((Action) axe_throw_failure).getOutput();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test throwing the axe again")
    public Boolean testThrowAxeSuccess(){
        Throw axe_throw_again = Throw("No Axe", "Throw", player_hit);
        expected = "No weapon to throw";
        actual = ((Action) axe_throw_failure).getOutput();
        assertEquals(expected, actual);
    }


}