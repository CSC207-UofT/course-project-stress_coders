package entities;

import entities.InteractableProperties;
import entities.Player;
import entities.Tree;
import entities.Variable;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class TreeTest {

    @Test
    public void addInfo() {
        Tree treeTest = new Tree("Tree");
        treeTest.setProperties(new HashMap<String, Variable>());
        treeTest.addInfo();

        assertTrue(treeTest.getProperties().containsKey(InteractableProperties.RES_NAME.name()));
        assertTrue(treeTest.getProperties().containsKey(InteractableProperties.RES_STORE_NAME.name()));
    }

    @Test
    public void harvest() {
        Tree treeTest = new Tree("Tree");
        treeTest.addInfo();
        Player playerTest = new Player("name");
        assertEquals("Added 10 wood to name inventory", treeTest.harvest(playerTest, 10));

        treeTest.setCompleted(true);
        assertEquals("No more left!", treeTest.harvest(playerTest, 10));
    }
}