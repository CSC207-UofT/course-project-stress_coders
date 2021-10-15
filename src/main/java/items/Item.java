package items;
import weaponiteminterfaces.CollectableObject;

public abstract class Item implements CollectableObject{
    private int numOfHealthPoints;

    public Item(int numOfHealthPoints){

        this.numOfHealthPoints = numOfHealthPoints;
    }

    public int getNumOfHealthPoints(){
        return this.numOfHealthPoints;
    }
    public void setNumOfHealthPoints(int numOfHealthPoints){
        this.numOfHealthPoints = numOfHealthPoints;
    }

}
