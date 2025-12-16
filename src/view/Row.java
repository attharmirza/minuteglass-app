package view;

import javax.swing.*;
import java.awt.*;

/**
 * A single row of "sand granules" in the MinuteGlass display.
 */
public class Row extends JPanel {
    /**
     * Possible states for a row.
     */
    public enum State {
        EMPTY, FILLED, PARTIAL
    }

    /**
     * The current state of the row.
     */
    private State currentState;

    /**
     * Setting the maximum size of a row, including hidden granules.
     */
    private int maxSize = Bulb.getBulbDimensions()[0];

    /**
     * The minimum size of a row is always 1 granule.
     */
    private int minSize = 1;

    /**
     * The current size of the row.
     */
    private int size;

    /**
     * Last granule state check distance, will always be a positive or negative
     * integer with an absolute value less than `maxSize / 2`.
     *
     * This is kind of confusing, but basically the granule removal algorithm
     * randomly selects a direction to check for granules to remove. The next check
     * will always be in the opposite direction, with the same distance. So the
     * algorithm will check this number multiplied by `-1`, before incrementing it.
     *
     * This is also initialized to `0`, representing the center of the row.
     */
    private int lastCheckDistance = 0;

    /**
     * Initializing an array of empty granules to fill the row.
     */
    private Granule[] granules = new Granule[maxSize];

    /**
     * Creates a row with the specified number of granules, after setting the
     * initial state for each.
     *
     * @param size The number of granules to add to the row.
     */
    public Row(int size, Granule.State initialState) {
        this.setLayout(new GridLayout(1, maxSize, 5, 0));
        this.setBackground(Theme.BACKGROUND);
        this.setOpaque(true);

        this.setSize(size);

        this.setRowState(initialState);

        for (Granule granule : granules) {
            this.add(granule);
        }
    }

    /**
     * Method that iterates through every granule in the row, checks the distance
     * from the center to see if it's state can be modified, and then modifies it.
     *
     * @param size
     * @throws IllegalArgumentException
     */
    private void setRowState(Granule.State state) {
        for (int i = 0; i < granules.length; i++) {
            int distanceFromCenter = Math.abs(i - (granules.length / 2));

            Granule toBeAdded = new Granule();

            if (distanceFromCenter == 0) {
                toBeAdded.setState(state);
                granules[i] = toBeAdded;
                continue;
            }

            if (distanceFromCenter <= size / 2) {
                toBeAdded.setState(state);
                granules[i] = toBeAdded;
                continue;
            }

            toBeAdded.setState(Granule.State.BLANK);
            granules[i] = toBeAdded;
        }
    }

    /**
     * Set the size of the Row, which practically translates to the number of
     * granules that are not set to BLANK and can have their state modified.
     *
     * @param size
     * @throws IllegalArgumentException
     */
    public void setSize(int size) throws IllegalArgumentException {
        if (size < minSize || size > maxSize) {
            throw new IllegalArgumentException("Size must be between " + minSize + " and " + maxSize);
        }

        if (size % 2 != 1) {
            throw new IllegalArgumentException("Size must be an odd number.");
        }

        this.size = size;
    }

    /**
     * Set the state of the row, only for use within the class.
     *
     * @param state
     */
    private void setState(State state) {
        currentState = state;
    }

    /**
     * Get the state of the row.
     *
     * @param state
     */
    public State getState(State state) {
        return currentState;
    }

    /**
     * Method for setting the row to empty.
     */
    public void emptyRow() {
        this.setRowState(Granule.State.EMPTY);
        this.setState(Row.State.EMPTY);
    }

    /**
     * Method for setting the row to full.
     */
    public void fillRow() {
        this.setRowState(Granule.State.FILLED);
        this.setState(Row.State.FILLED);
    }

    /**
     * Remove a single granule from the row.
     */
    public void removeGranule() {
        if (this.currentState == Row.State.EMPTY) {
            return;
        }

        if (Math.abs(this.lastCheckDistance) > maxSize / 2) {
            this.setState(Row.State.EMPTY);
            return;
        }

        this.setState(Row.State.PARTIAL);

        int centerIndex = maxSize / 2;
        int checkIndexA = this.lastCheckDistance + centerIndex;
        int checkIndexB = (this.lastCheckDistance * -1) + centerIndex;

        if (lastCheckDistance == 0) {
            this.granules[centerIndex].setState(Granule.State.EMPTY);

            if (Math.random() >= 0.5)
                this.lastCheckDistance = 1;
            else
                this.lastCheckDistance = -1;
            return;
        }

        Granule.State stateSideA = this.granules[checkIndexA].getState();
        Granule.State stateSideB = this.granules[checkIndexB].getState();

        if (stateSideA == Granule.State.EMPTY && stateSideB == Granule.State.EMPTY) {
            if (Math.random() >= 0.5) {
                this.lastCheckDistance = Math.abs(this.lastCheckDistance) + 1;
            } else {
                this.lastCheckDistance = -Math.abs(this.lastCheckDistance) - 1;
            }

            removeGranule();
        }

        if (stateSideA == Granule.State.EMPTY) {
            this.granules[checkIndexB].setState(Granule.State.EMPTY);
        } else if (stateSideB == Granule.State.EMPTY) {
            this.granules[checkIndexA].setState(Granule.State.EMPTY);
        }
    }
}
