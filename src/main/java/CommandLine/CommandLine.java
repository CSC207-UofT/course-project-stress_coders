package CommandLine;
import java.util.Scanner;
import Constants.*;
import GameController.*;
public class CommandLine {
    public final static GameCompiler mainCompiler = new GameCompiler();
    public static void main(String[] args) {
        boolean running = true;
        Scanner input = new Scanner(System.in);
        while (running) {
            System.out.println(mainCompiler.getDialogue());
            System.out.print("Your command: ");

            String formattedInput = input.nextLine();
            if (formattedInput.equals("exit")) {
                running = false;
            }
            else if (!CommandConstants.isCommand(formattedInput)) {
                System.out.println("This is not a command, try again!");
            }
            else {
                String response = mainCompiler.run(formattedInput);
                System.out.println(response);
            }
        }
        input.close();
    }
}
