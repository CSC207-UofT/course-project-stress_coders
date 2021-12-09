package interfaceadapters;

import Style.ColorConstants;
import entities.characters.Player;
import frameworks.CommandLine;
import com.google.gson.Gson;
import usecases.Encounter;
import usecases.PlayerManager;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/*
Save and load gameState to one of the files
 */
public class GameStateSaver {

    private final Gson gson = new Gson();
    private final EncounterSerializer ES = new EncounterSerializer();
    private final GamestateSerializer GS = new GamestateSerializer();

   /*
   Standard method to write to a file.

   This specifically saves a gamester object in a useful format:

   Encounter Number:
   ---- Value ---
   Player Data:
   --- Data ---
   Encounters:
   encounter data 1
   encounter data 2
   ...
   encounter data n
    */
    public void saveToFile(String FileName, GameState gs){
        try {
            File file = new File("saves/save" + FileName + ".txt");

            if (!file.exists()){
                System.out.println("Invalid Save File");
                return;
            }

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("Encounter Number:");
            bufferedWriter.newLine();
            bufferedWriter.write("" + gs.encounterNumber());
            bufferedWriter.newLine();
            bufferedWriter.write("Player Data:");
            bufferedWriter.newLine();
            bufferedWriter.write(GS.serializePlayer(gs.getPlayerState().getPlayer()));
            bufferedWriter.newLine();
            bufferedWriter.write("Encounter Data:");
            bufferedWriter.newLine();

            for(Encounter encounter : gs.getEncounters()){
                bufferedWriter.write(ES.serializeEncounter(encounter));
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

            System.out.println("Data saved to file " + FileName);

        } catch (IOException e) {
            System.out.println("error writing to file");
        }
    }

    /*
    Read saved data from one of the files and reconstruct the game state object and then restart the command line
    with the new gameState object.

    Assuming the file is in the format above.

    IF not print an error.

    If the save is empty print an error.

    Game state is reconstructed by first getting the encounter number (it's an int, so we can save it as a string
    and use parseInt )

    Deserialize the player this is needed for an instance variable of gamestate and needs to be done before
    deserializing the encounters as outlined in the encounterSerializer class

    Deserialize all the encounters put them in a list assign it to the correct instance variable and then
    construct the last 2 remaining instance vars.
     */
    public void loadGame(String saveFile) throws IOException, CloneNotSupportedException {
        BufferedReader in = new BufferedReader(new FileReader("saves/save" + saveFile + ".txt"));
        String line = in.readLine();

        if(line.length() < 2){
            System.out.println("Empty Save File");
            return;
        }

        GameState gs = new GameState();
        Player p;
        if(!line.equals("Encounter Number:")){
            System.out.println("corrupted save, could not load");
            return;
        } else {
            line = in.readLine();
            gs.setCurrent_encounter(Integer.parseInt(line));
        }
        line = in.readLine();
        if(!line.equals("Player Data:")){
            System.out.println("corrupted save, could not load");
            return;
        } else {
            line = in.readLine();
            p = GS.deserializePlayer(line);
            gs.setPlayerManager(new PlayerManager(p));
        }
        line = in.readLine();
        if(!line.equals("Encounter Data:")){
            System.out.println("corrupted save, could not load");
            return;
        } else {
            line = in.readLine();
            ArrayList<Encounter> encounters = new ArrayList<>();
            while(line != null && !line.isBlank() && !line.isEmpty()){
                encounters.add(ES.deserializeEncounter(p, line));
                line = in.readLine();
            }
            gs.setEncounters(encounters);
        }
        fillGameState(gs);

        in.close();
        CommandLine commandLine = new CommandLine(gs);
        System.out.println("Save File Loaded!");

        System.out.println("Current Quest: " + gs.getCurrent_encounter().getDetails());
        System.out.println("Your current mission: " + ColorConstants.getColorCode("RED") +
                gs.getCurrent_encounter().getProgression().get(gs.getCurrent_encounter().getCurrInteractableIndex()).getId() +
                ColorConstants.getColorCode("RESET"));
        System.out.println(ColorConstants.getColorCode("PURPLE") + gs.getHelp());
        System.out.println(ColorConstants.getColorCode("RESET") + gs.getPlayerState().getPlayerInfo());
        commandLine.setPlayerState(gs.getPlayerState());
        commandLine.run();
    }


    /*
    We don't save all the variables of gamestate since some of them can be constructed from the other 3.

    Here we construct the other 2 varies and assign them to a game state object assuming the other 3
    variables are already assigned.
     */
    private void fillGameState(GameState gs){
        gs.setCompletedEncounters(new ArrayList<>());
        for(Encounter e : gs.getEncounters()){
            if (e.isCompleted()){
                gs.getCompletedEncounters().add(e);
            }
        }
        gs.setEncounterConversion(new HashMap<>());
        for(Encounter e : gs.getEncounters()){
            gs.getEncounterConversion().put(e.getName(), e);
        }
    }

    /*
    This checks to make sure a save is valid
     */
    public boolean validSaveFile(String fileName) throws IOException {
        File file = new File("saves/save" + fileName + ".txt");

        if(!file.exists()){
            System.out.println("Invalid Save File");
            return false;
        } else {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line = in.readLine();

            if (line == null || line.length() < 2){
                System.out.println("Empty Save File");
                return false;
            } else {
                return true;
            }
        }
    }
}
