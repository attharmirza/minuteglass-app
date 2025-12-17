package controller;

import view.*;
import model.TimerListener;
import model.Timer;

/**
 * This class is my best attempt at following the Model-View-Controller pattern.
 *
 * It takes the Timer class from the model package and links it to the view
 * package, specifically to the Glass class in view, where the remove granule
 * algorithm exists.
 *
 * @hidden Maybe, if I had more time, I would move the cascading, repeated
 *         algorithms in the view package for "removing a granule" into the
 *         model package, and create interfaces for them. There should probably
 *         at least be an interface for any "view" class that is responsible for
 *         handling the display of sand granules, because they should all
 *         implement methods for emptying, filling, and removing granules.
 *         Anyway, I'm not sure if this is the best way to do it, but it's a
 *         start.
 */
public class AnimationController implements TimerListener {
    /**
     * Storing an instance of the MinuteGlass for triggering events.
     */
    private Glass glass;

    /**
     * The local instance of the Timer that is driving this animation.
     */
    private Timer timer;

    /**
     * The total number of intervals will always be equal to the total capacity of
     * the MinuteGlass.
     */
    private int totalTimerIntervals;

    /**
     * Single argument constructor for AnimationController, initializes it with a
     * default length timer.
     *
     * @param glass The MinuteGlass to be animated
     */
    public AnimationController(Glass glass) {
        this.glass = glass;
        this.timer = new Timer();
    }

    /**
     * Double argument constructor for AnimationController, initializes it with a
     * custom length timer.
     *
     * @param glass The MinuteGlass to be animated
     * @param timer The Timer to be used for the animation
     */
    public AnimationController(Glass glass, Timer timer) {
        this.glass = glass;
        this.timer = timer;
    }

    /**
     * Convenience method to set the Timer to be a certain number of minutes.
     */
    public void setMinutesTimer(int minutes) {
        this.timer = new Timer(minutes, 0, 0);
    }

    /**
     * Convenience method to set the timer to be a certain number of seconds.
     */
    public void setSecondsTimer(int seconds) {
        this.timer = new Timer(0, seconds, 0);
    }

    /**
     * Start the minuteglass animation.
     */
    public void startAnimation() {
        // System.out.println("Total sand: " + Bulb.getTotalSand());

        this.timer.setTickListener(this);

        this.totalTimerIntervals = Bulb.getTotalSand();

        this.timer.startTimer(totalTimerIntervals);
    }

    /**
     * Called on each timer tick to animate the next granule.
     */
    public void onTick() {
        glass.moveGranule();

        // System.out.println(timer.getRemainingTimeAsString());
    }
}
