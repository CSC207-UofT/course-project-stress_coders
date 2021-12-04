package entities;

import entities.minigames.VaultDoor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VaultDoorTest {

    @Test
    public void userAttempt() {
        String[] hints = {"hint1", "hint2"};
        VaultDoor doorTest = new VaultDoor("door", hints, "answer!");

        assertEquals("Oops! Incorrect code..try again.", doorTest.userAttempt("this is a wrong answer AH AH AH AH"));
        assertEquals("Nice! Your cracked the code.", doorTest.userAttempt("answer!"));
    }
}