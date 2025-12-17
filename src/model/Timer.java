package model;

/**
 * The core functionality of the hourglass, keeps track of the total number of
 * seconds the minuteglass was initialezed with, and the number of seconds
 * remaining.
 */
public class Timer {
    /**
     * Total time in milliseconds.
     */
    private int totalTime;

    /**
     * Remaining time in milliseconds.
     */
    private int remainingTime;

    /**
     * Listener that is notified when the timer ticks.
     */
    private TimerListener tickListener;

    /**
     * No argument constructor, initializes the Timer to 5 minutes.
     */
    public Timer() {
        this(5 * 60 * 1000);
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
        this(seconds * 1000 + milliseconds);
    }

    /**
     * Constructor that initializes the Timer to a specific number of minutes and
     * seconds.
     */
    public Timer(int minutes, int seconds, int milliseconds) {
        this(minutes * 60 * 1000 + seconds * 1000 + milliseconds);
    }

    /**
     * Get remaining time as a nice string.
     */
    public String getRemainingTimeAsString() {
        int totalSeconds = remainingTime / 1000;
        int minutes = (int) totalSeconds / 60;
        float seconds = totalSeconds % 60 + (remainingTime % 1000) / 1000f;

        return String.format("Remaining time: %02d:%06.3f", minutes, seconds);
    }

    /**
     * Set the tick listener for the timer.
     */
    public void setTickListener(TimerListener listener) {
        this.tickListener = listener;
    }

    /**
     * Starts a timer loop that chops up the length of the timer into a total number
     * of intervals, and then decrements the remaining time with a thread sleep.
     *
     * @param pieces The number of intervals to chop the timer into.
     */
    public void startTimer(int pieces) {
        int interval = this.totalTime / pieces;

        System.out.println("Total Time: " + this.totalTime);
        System.out.println("Pieces: " + pieces);
        System.out.println("---------------------");
        System.out.println("Interval: " + interval);
        System.out.println("Pieces Needed: " + this.totalTime / interval);

        while (this.remainingTime > 0) {
            this.remainingTime -= interval;

            if (tickListener != null) {
                tickListener.onTick();
            }

            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        remainingTime = 0;
    }
}
