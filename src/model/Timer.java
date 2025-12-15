package model;

/**
 * The core functionality of the hourglass, keeps track of the total number of
 * seconds the minuteglass was initialezed with, and the number of seconds
 * remaining.
 */
public class Timer {
    private int totalSeconds;
    private int remainingSeconds;

    /**
     * No argument constructor, initializes the Timer to 5 minutes.
     */
    public Timer() {
        this.totalSeconds = 60 * 5;
        this.remainingSeconds = this.totalSeconds;
    }

    /**
     * Constructor that initializes the Timer to a specific number of seconds.
     * 
     * @param seconds Initial timer length
     */
    public Timer(int seconds) {
        this.totalSeconds = seconds;
        this.remainingSeconds = this.totalSeconds;
    }
}
