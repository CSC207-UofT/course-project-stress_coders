import java.util.HashMap;

public abstract class Interactable {
    private String id;
    private HashMap<String, Variable> properties = new HashMap<>();

    public Interactable(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public void addProperty(String name, Variable variable){
        properties.put(name, variable);
    }

    public Variable getProperty(String name){
        return properties.get(name);
    }
}
