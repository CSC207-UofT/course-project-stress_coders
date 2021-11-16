package frameworks;

import Style.ColorConstants;

import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        System.out.println(ColorConstants.getColorCode("CYAN") + "Welcome to Stress Coder's CSC207 Project! We hope you enjoy!" +
                ColorConstants.getColorCode("RESET"));
        CommandLine cl = new CommandLine();
        cl.start();
    }
}
