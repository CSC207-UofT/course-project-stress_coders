package entities;

import interfaceadapters.SystemTimeable;
import usecases.Time;
import usecases.Timing;

public class Timer {

    private double currentTime;
    private double maxTime;
    private double startingTime;
    private Timing time;


    /**
     * Construct a new Timer object
     *
     * @param current the current time elapsed
     * @param max the max time for the time to fail
     * @param time the time getter
     */
    public Timer(double current, double max, Timing time){
        this.currentTime = current;
        this.maxTime = max;
        this.time = time;
        this.startingTime = time.returnTime();
    }

    /**
     * Get the current time elapsed
     *
     * @return currentTime
     */
    public double getCurrentTime(){
        return this.currentTime;
    }


    /**
     * Get the max time allowed elapsed
     *
     * @return maxTime
     */
    public double getMaxTime(){
        return this.maxTime;
    }

    /**
     * Add time to the max time
     *
     * @param addMax the amount of time to be added
     */
    public void updateMaxTime(double addMax){
        this.maxTime = this.maxTime + addMax;
    }

    /**
     *
     * @return whether there is no more time left or not
     */
    public boolean hasTimeElapsed(){
        return this.currentTime > this.maxTime;
    }

    public void updateTime(){
        this.currentTime = this.time.returnTime() - this.startingTime;
    }


}
