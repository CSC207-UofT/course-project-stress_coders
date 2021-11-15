package frameworks;

import interfaceadapters.IDreader;
import usecases.Encounter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
//        IDreader iDreader = new IDreader();
//        Encounter testEncoutner = new Encounter();
        System.out.println("Welcome to Stress Coder's CSC207 Project! We hope you enjoy!");
        CommandLine cl = new CommandLine();
        cl.start();
    }
}
