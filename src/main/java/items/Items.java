package items;
import java.util.List;
import item.Item;

public class Items {
    private List<Item> itemsAvailable;

    public Items(List<Item> items){

        this.itemsAvailable = items;
    }

    public void setItemsAvailable(List<Item> items){
        this.itemsAvailable = items;
    }
    public List<Item> getItemsAvailable(){
        return this.itemsAvailable;
    }
    public void addItem(Item item){
        this.itemsAvailable.add(item);
    }
    public void removeItem(Item item){
        this.itemsAvailable.remove(item);
    }
}
