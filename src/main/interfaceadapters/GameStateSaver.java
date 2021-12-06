package interfaceadapters;

import Style.ColorConstants;
import entities.minigames.VaultDoor;
import entities.characters.Player;
import frameworks.CommandLine;
import com.google.gson.Gson;
import usecases.Encounter;
import usecases.PlayerManager;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/*
Save and load gameState
 */
public class GameStateSaver {

    private final Gson gson = new Gson();
    private final EncounterSerializer ES = new EncounterSerializer();
    private final GamestateSerializer GS = new GamestateSerializer();

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

        System.out.println("Current Encounter: " + gs.getCurrent_encounter().getDetails());
        System.out.println(ColorConstants.getColorCode("PURPLE") + gs.getHelp());
        System.out.println(ColorConstants.getColorCode("RESET") + gs.getPlayerState().getPlayerInfo());

        commandLine.setPlayerState(gs.getPlayerState());
        commandLine.run();
    }

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


    private GameState jsonToGameState(String JSON){
        return gson.fromJson(JSON, GameState.class);
    }

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
