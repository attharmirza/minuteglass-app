package view;

import javax.swing.*;

/**
 * A single "sand granule," which when placed together form a row for the
 * MinuteGlass display.
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
     * Method for setting the state of the row, and repainting at the end.
     *
     * @param state The new state of the row.
     */
    public void setState(State state) {
        currentState = state;

        switch (currentState) {
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
}
