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
    private int maxSize = 21;

    /**
     * The minimum size of a row is always 1 granule.
     */
    private int minSize = 1;

    /**
     * Initializing an array of empty granules to fill the row.
     */
    private Granule[] granules = new Granule[maxSize];

    /**
     * Creates a default row of 20 empty granules if no granules are provided.
     */
    public Row() {
        this(20);
    }

    /**
     * Creates a row with the specified number of granules.
     *
     * @param size The number of granules to add to the row.
     */
    public Row(int size) {
        this.setLayout(new GridLayout(1, maxSize, 5, 0));
        this.setBackground(Theme.BACKGROUND_COLOR);
        this.setOpaque(true);

        this.setSize(size);

        for (Granule granule : granules) {
            this.add(granule);
        }
    }

    /**
     *
     * @param size
     * @throws IllegalArgumentException
     */
    private void setSize(int size) throws IllegalArgumentException {
        if (size < minSize || size > maxSize) {
            throw new IllegalArgumentException("Size must be between " + minSize + " and " + maxSize);
        }

        if (size % 2 != 1) {
            throw new IllegalArgumentException("Size must be an odd number.");
        }

        for (int i = 0; i < granules.length; i++) {
            int distanceFromCenter = Math.abs(i - (granules.length / 2));

            Granule toBeAdded = new Granule();

            if (distanceFromCenter == 0) {
                toBeAdded.setState(Granule.State.FILLED);
                granules[i] = toBeAdded;
                continue;
            }

            if (distanceFromCenter <= size / 2) {
                toBeAdded.setState(Granule.State.FILLED);
                granules[i] = toBeAdded;
                continue;
            }

            toBeAdded.setState(Granule.State.BLANK);
            granules[i] = toBeAdded;
        }
    }
}
