package commands;
import characters.Player;


public abstract class Action{

    private final String input;
    protected final Player user;
    protected final String name;

    /**
     * Initialize the action based on the input
     *
     * @param input the input for the action
     * @param name the name of the action
     * @param user the current state of the user with it's inventory
     */
    public Action(String input, String name, Player user){
        this.input = input;
        this.user = user;

        this.name = name;
    }

    public String getInput() {
        return this.input;
    }



    abstract public String performAction();

}