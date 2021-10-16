package items;
import weaponiteminterfaces.CollectableObject;

public abstract class Item implements CollectableObject{
    /**
     * abstarct Item that implements interface CollectableObject
     * Item has attribute number of health points the item provides
     */
    private int numOfHealthPoints;

    /**
     * Constructs an Item with attributes numOfHealthPoints
     *
     * @param numOfHealthPoints   int of how many health points this Item provides with
     */
    public Item(int numOfHealthPoints){

        this.numOfHealthPoints = numOfHealthPoints;
    }

    /**
     * return the number of health points of this Item.
     *
     * @return numOfHealthPoints   the integer number of health points of this Item
     */
    public int getNumOfHealthPoints(){
        return this.numOfHealthPoints;
    }

    /**
     * set the number of health points of this Item.
     *
     * @param numOfHealthPoints    the number of health points of this Item
     */
    public void setNumOfHealthPoints(int numOfHealthPoints){
        this.numOfHealthPoints = numOfHealthPoints;
    }

}
