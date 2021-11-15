import entities.Character;
import entities.Player;
import interfaceadapters.BuilderSetup;
import org.junit.Test;
import usecases.Encounter;
import usecases.IDreader;
import usecases.PlayerManager;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;


public class BuilderSetupTest {
    @Test
    public void builderLengthTestShort() throws CloneNotSupportedException, IOException {
        IDreader idReader = new IDreader();
        PlayerManager p = new PlayerManager("sh", "easy");
        BuilderSetup b = new BuilderSetup(p.getPlayer(), "short");
        List<Encounter> encounters = b.build();
        assertTrue(encounters.size() <= 10);
        for (Encounter e: encounters) {
            assertTrue(e.getGenericPool().size() <= 5);}
    }

    @Test
    public void builderLengthTestMedium() throws CloneNotSupportedException, IOException {
        IDreader idReader = new IDreader();
        PlayerManager p = new PlayerManager("sh", "easy");
        BuilderSetup b = new BuilderSetup(p.getPlayer(), "medium");
        List<Encounter> encounters = b.build();
        assertTrue(encounters.size() <= 20);
        for (Encounter e: encounters) {assertTrue(e.getGenericPool().size() <= 10);}
    }

    @Test
    public void builderLengthTestLong() throws CloneNotSupportedException, IOException {
        IDreader idReader = new IDreader();
        PlayerManager p = new PlayerManager("sh", "easy");
        BuilderSetup b = new BuilderSetup(p.getPlayer(), "long");
        List<Encounter> encounters = b.build();
        assertTrue(encounters.size() <= 30);
        for (Encounter e: encounters) {assertTrue(e.getGenericPool().size() <= 15);}
    }
}
