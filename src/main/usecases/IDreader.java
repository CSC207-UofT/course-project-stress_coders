package usecases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/*
Singleton
 */
public class IDreader {
    public static HashMap<String, Float> ObjAdjectives = new HashMap<String, Float>();
    public static HashMap<String, Float> CharAdjectives = new HashMap<String, Float>();

    public IDreader() throws IOException{
        initAdjectives();
    }

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

    private void initAdjectives() throws IOException{
        String CHAR_FILE = "resources/CharacterAdjectives.txt";
        IDreader.CharAdjectives = readAdjectiveFile(CHAR_FILE);
        String OBJ_FILE = "resources/ObjectAdjectives.txt";
        IDreader.ObjAdjectives = readAdjectiveFile(OBJ_FILE);
    }
}
