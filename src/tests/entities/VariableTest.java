package entities;

import entities.Variable;
import org.junit.Test;

import static org.junit.Assert.*;

public class VariableTest {

    @Test
    public void setBool() {
        Variable testVar = new Variable(false);
        testVar.setBool(true);

        assertTrue(testVar.getBool());

        testVar.setBool(false);
        assertFalse(testVar.getBool());
    }

    @Test
    public void setInteger() {
        Variable testVar = new Variable(0);
        testVar.setInteger(1);

        assertEquals(1, testVar.getInteger());
    }

    @Test
    public void setStr() {
        Variable testVar = new Variable("test");
        testVar.setStr("test2");

        assertEquals("test2", testVar.getStr());
    }

    @Test
    public void getBool() {
        Variable testVar = new Variable(false);
        testVar.setBool(true);

        assertTrue(testVar.getBool());

        testVar.setBool(false);
        assertFalse(testVar.getBool());
    }

    @Test
    public void getStr() {
        Variable testVar = new Variable("test");
        testVar.setStr("test2");

        assertEquals("test2", testVar.getStr());
    }

    @Test
    public void getInteger() {
        Variable testVar = new Variable(0);
        testVar.setInteger(1);

        assertEquals(1, testVar.getInteger());
    }
}