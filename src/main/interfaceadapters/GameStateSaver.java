package interfaceadapters;

import Style.ColorConstants;
import entities.VaultDoor;
import frameworks.CommandLine;
import com.google.gson.Gson;

import java.io.*;

/*
Save and load gameState
 */
public class GameStateSaver {

    private final Gson gson = new Gson();

    private String gameStateToJSON(GameState gs){
        return gson.toJson(gs);
    }

    public void saveToFile(String FileName, GameState gs){
        try {
            String gsSave = gameStateToJSON(gs);
            File file = new File("saves/save" + FileName + ".txt");

            if (!file.exists()){
                System.out.println("Invalid Save File");
                return;
            }

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(gsSave);
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

        GameState gs = jsonToGameState(line);
        in.close();
        CommandLine commandLine = new CommandLine(gs);
        System.out.println("Save File Loaded!");

        System.out.println("Current Encounter: " + gs.getCurrent_encounter().getDetails());
        System.out.println(ColorConstants.getColorCode("PURPLE") + gs.getHelp());
        System.out.println(ColorConstants.getColorCode("RESET") + gs.getPlayerState().getPlayerInfo());

        commandLine.setPlayerState(gs.getPlayerState());
        System.out.println(gs.getCurrent_encounter().objIDs.get("Vault door"));
        commandLine.run();
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
