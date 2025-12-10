package view;

import javax.swing.*;
import java.awt.*;

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
    public enum State {
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
                this.setBackground(Color.GRAY);
                break;
            case FILLED:
                this.setBackground(Color.BLACK);
                break;
            case BLANK:
                this.setBackground(Color.WHITE);
                break;
        }

        repaint();
    }
}
