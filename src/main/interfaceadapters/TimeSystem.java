package interfaceadapters;

public class TimeSystem implements SystemTimeable{

    @Override
    public double getSystemTime() {
        return System.currentTimeMillis();
    }
}
