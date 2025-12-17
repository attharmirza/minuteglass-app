package view;

import javax.swing.*;

/**
 * A single "sand granule," which when placed together form a row for the
 * Minuteglass display.
 */
public class Granule extends JPanel {
    /**
     * Constructor for a single granule in the row.
     */
    public Granule() {
        this.setOpaque(true);
    }

    /**
     * All the possible states of a row.
     */
    public static enum State {
        EMPTY, FILLED, BLANK
    }

    /**
     * The current state of the row.
     */
    private State currentState = State.EMPTY;

    /**
     * Method for setting the state of the granule, and repainting at the end.
     *
     * @param state The new state of the granule.
     */
    public void setState(State state) {
        this.currentState = state;

        switch (state) {
            case EMPTY:
                this.setBackground(Theme.SAND_OFF);
                break;
            case FILLED:
                this.setBackground(Theme.SAND_ON);
                break;
            case BLANK:
                this.setBackground(Theme.BACKGROUND);
                break;
        }

        repaint();
    }

    /**
     * Method for getting the state of the row.
     *
     * @return The current state of the row.
     */
    public State getState() {
        return currentState;
    }
}
