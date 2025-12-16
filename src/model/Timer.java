package model;

/**
 * The core functionality of the hourglass, keeps track of the total number of
 * seconds the minuteglass was initialezed with, and the number of seconds
 * remaining.
 */
public class Timer {
    private int totalTime;
    private int remainingTime;

    /**
     * No argument constructor, initializes the Timer to 5 minutes.
     */
    public Timer() {
        this.totalTime = 5 * 60 * 1000;
        this.remainingTime = this.totalTime;
    }

    /**
     * Constructor that initializes the Timer to a specific number of milliseconds.
     *
     * @param milliseconds Initial timer length
     */
    public Timer(int milliseconds) {
        this.totalTime = milliseconds;
        this.remainingTime = this.totalTime;
    }

    /**
     * Constructor that initializes the Timer to a specific number of seconds and
     * milliseconds.
     */
    public Timer(int seconds, int milliseconds) {
        this.totalTime = seconds * 1000 + milliseconds;
        this.remainingTime = this.totalTime;
    }

    /**
     * Constructor that initializes the Timer to a specific number of minutes and
     * seconds.
     */
    public Timer(int minutes, int seconds, int milliseconds) {
        this.totalTime = minutes * 60 * 1000 + seconds * 1000 + milliseconds;
        this.remainingTime = this.totalTime;
    }

    /**
     * Starts a timer loop that chops up the length of the timer into a total number
     * of intervals, and then decrements the remaining time with a thread sleep.
     *
     * @param pieces The number of intervals to chop the timer into.
     */
    public void startTimer(int pieces) {
        int interval = totalTime / pieces;

        while (remainingTime > 0) {
            remainingTime -= interval;

            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
