package entities;

/**
params of commands require variables to be executed. I.e. a throwable object that is the thrown_obj parameter
 of the thrown command needs a weight and hitProbability. Interactables can have multiple qualities
 I.e. Throwable and edible, but they cannot extend multiple classes to enforce the existence of each
 quality's required instance variables.
So those properties are stored in interactable.properties Interactable as instances of this class
 @see Interactable
I.e. a throwable interactable.properties would contain {"weight": VarObj1, "hitProbability": VarObj2},
 an edible interactable.properties would contain {"health_restored": VarObj3} and a throwable and edible must contain
 all 3 VarObjs.
 So VarObj1.getInteger would return the weight as an integer, and all other getters of VarObj 1
 would return default values, since weight is represented with an integer.
 @see entities.interfaces.Throwable
 for how varaible existence is enforced
 @see Axe
 for an implementation example of how variables are added to interactables
 @see usecases.Throw
 For how these properties are accessed

VarObjs only hold one of bool, str, integer representing the value of the property.
 **/
public class Variable {
    /*
    Value of the Variable, can only be one of these 3,
     */
    private boolean bool;
    private String str;
    private int integer;

    public Variable(boolean bool){
        this.bool = bool;
    }

    public Variable(String s){
        this.str = s;
    }

    public Variable(int integer){
        this.integer = integer;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    public void setInteger(int integer) {
        this.integer = integer;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public boolean getBool(){
        return this.bool;
    }

    public String getStr(){
        return this.str;
    }

    public int getInteger(){
        return this.integer;
    }
}
