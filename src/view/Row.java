package view;

import javax.swing.*;
import java.awt.*;

/**
 * A single row of "sand granules" in the MinuteGlass display.
 */
public class Row extends JPanel {
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
     * Method for setting the row to empty.
     */
    public void emptyRow() {
        this.setRowState(Granule.State.EMPTY);
    }

    /**
     * Method for setting the row to full.
     */
    public void fillRow() {
        this.setRowState(Granule.State.FILLED);
    }
}
