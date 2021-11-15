package usecases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/*
Singleton reads the adjectives from the adjectives files and puts them in the appropriate maps
 */
public class IDreader {
    public static HashMap<String, Float> ObjAdjectives = new HashMap<>();
    public static HashMap<String, Float> CharAdjectives = new HashMap<>();

    public IDreader() throws IOException{
        initAdjectives();
    }

    /*
    Read an adjective file in the format:
    weight1: adjective1, adjective2, ..., adjectiveN
    weight1: adjective1, adjective2, ..., adjectiveN

    create a mapping between adjectives and weights to return.
     */
    private HashMap<String, Float> readAdjectiveFile(String fileName) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String line = in.readLine();

        HashMap<String, Float> adjectiveToWeight = new HashMap<>();
        String regex1 = ":";
        String regex2 = ",";

        int adjectives = 1;
        int weight = 0;
        while (line.contains(regex1)) {
            String[] components = line.split(regex1);
            String[] adjectiveList = components[adjectives].split(regex2);

            for (String adjective : adjectiveList) {
                adjectiveToWeight.put(adjective.trim(), Float.parseFloat(components[weight]));
            }
            line = in.readLine();
        }

        return adjectiveToWeight;
    }

    /*
     Load the 2 adjective files.
     */
    public void initAdjectives() throws IOException{
        String CHAR_FILE = "resources/CharacterAdjectives.txt";
        IDreader.CharAdjectives = readAdjectiveFile(CHAR_FILE);
        String OBJ_FILE = "resources/ObjectAdjectives.txt";
        IDreader.ObjAdjectives = readAdjectiveFile(OBJ_FILE);
    }
}
