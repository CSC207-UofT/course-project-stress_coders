package entities.minigames;

public class Timer {

    private double currentTime;
    private double maxTime;
    private double startingTime;

    /**
     * Construct a new Timer object
     *
     * @param current the current time elapsed
     * @param max the max time for the time to fail
     */
    public Timer(double current, double max){
        this.currentTime = current;
        this.maxTime = max;

        //TODO: move out of entity layer
        this.startingTime = System.currentTimeMillis();
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
     * Set the current time elapsed
     *
     * @param current the new amount of time elapsed
     */
    public void setCurrentTime(double current){
        this.currentTime = current;
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
        return this.currentTime < this.maxTime;
    }

    public void updateTime(){
        this.currentTime = System.currentTimeMillis() - this.startingTime;
    }


}
