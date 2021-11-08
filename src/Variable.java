public class Variable {
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
