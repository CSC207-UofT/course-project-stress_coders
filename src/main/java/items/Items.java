package items;
import java.util.ArrayList;
import item.Item;

public class Items<T> {
    private ArrayList<Item> itemsAvailable;

    public Items(ArrayList<Item> items){
        this.itemsAvailable = items;
    }

    public void setItemsAvailable(ArrayList<Item> items){
        this.itemsAvailable = items;
    }
    public ArrayList<Item> getItemsAvailable(){
        return this.itemsAvailable;
    }
    public void addItem(Item item){
        this.itemsAvailable.add(item);
    }
    public void removeItem(Item item){
        this.itemsAvailable.remove(item);
    }
}
