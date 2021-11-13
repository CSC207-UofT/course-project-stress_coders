package entities;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class DoorTest {

    @Test
    public void isUnlocked() {
        String[] hints = {"How are you doing Mr wonderful. also known as Shouer Wang!"};
        Door doorTest = new VaultDoor("door", hints, "answer!");

        doorTest.userAttempt("lmao");
        assertFalse(doorTest.isUnlocked());

        doorTest.userAttempt("answer!");
        assertTrue(doorTest.isUnlocked());
    }

    @Test
    public void provideHint() {
        String[] hints = {"How are you doing Mr wonderful. also known as Shouer Wang!"};
        Door doorTest = new VaultDoor("door", hints, "answer!");

        assertEquals("How are you doing Mr wonderful. also known as Shouer Wang!", doorTest.provideHint());
        assertEquals("Sorry you're out of hints!", doorTest.provideHint());

    }

    @Test
    public void viewAllHints() {
        String[] hints = {"hint1", "hint2"};
        Door doorTest = new VaultDoor("door", hints, "answer!");
        assertEquals("hint1" + "\n" + "hint2", doorTest.viewAllHints());
    }

    @Test
    public void resetHints() {
        String[] hints = {"hint1", "hint2"};
        Door doorTest = new VaultDoor("door", hints, "answer!");
        doorTest.provideHint();

        doorTest.resetHints();
        assertEquals(0, doorTest.currentHint);
    }

    @Test
    public void userAttempt() {
        String[] hints = {"hint1", "hint2"};
        Door doorTest = new VaultDoor("door", hints, "answer!");

        assertEquals("Oops! Incorrect code..try again.", doorTest.userAttempt("this is a wrong answer AH AH AH AH"));
        assertEquals("Nice! Your cracked the code.", doorTest.userAttempt("answer!"));

    }
}