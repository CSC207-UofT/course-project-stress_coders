import java.util.HashMap;
import java.util.List;

public abstract class Command {
    private String description = "No description";
    private HashMap<String, List<String>> validParams;
    // {"thrown_obj": ["Throwable"], "target": ["ThrowableTarget", "Enemy", "Tree"]}

    public Command(){}

    public abstract String execute(HashMap<String, Interactable> args);

    public String help(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
