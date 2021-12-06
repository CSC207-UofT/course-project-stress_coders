package usecases;

import interfaceadapters.SystemTimeable;

public class Time implements Timing{

    public SystemTimeable systemTimer;

    public Time(SystemTimeable st){
        this.systemTimer = st;
    }

    /*
    Call on the systemTimeable interface to get the time from the system and return it
     */
    @Override
    public double returnTime() {
        return this.systemTimer.getSystemTime();
    }
}
