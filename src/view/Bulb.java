package view;

import javax.swing.*;
import java.awt.*;

/**
 * Represents one of two bulbs in the MinuteGlass.
 */
public class Bulb extends JPanel {
    /**
     * Position of the bulb, helps place the funnel.
     */
    public static enum Position {
        BOTTOM, TOP
    }

    /**
     * Total amound of sand granules in the MinuteGlass.
     */
    private static int totalSand = 0;

    /**
     * Number of rows of bulbs in the glass. Only defining one bulb because I want
     * it to always be an even number. This will get multiplied by 2 to get the
     * total number of rows.
     */
    private static int bulbHeight = 21;

    /**
     * Number of granules in each row of the bulbs, should always be an odd number.
     */
    private static int bulbWidth = 21;

    /**
     * An array of integers representing the shape of the MinuteGlass funnel. It
     * must be an array of odd numbers.
     */
    private static int[] funnelShape = { 19, 15, 9, 5, 3, 1 };

    /**
     * Creates an hourglass "bulb" of the predecided dimensions, set using static
     * variables.
     *
     * @param position The position of the bulb, either BOTTOM or TOP.
     */
    public Bulb(Position position) {
        this.setPreferredSize(new Dimension(200, 400));
        this.setLayout(new GridLayout(Bulb.bulbHeight, 1, 0, 5));
        this.setBackground(Theme.BACKGROUND);

        if (position == Position.BOTTOM) {
            createBottomBulb();
        } else {
            createTopBulb();
        }
    }

    /**
     * Sets the shape of the funnel.
     *
     * @param shape An array of odd numbers representing the shape of the funnel.
     * @throws IllegalArgumentException If the shape is not an array of odd numbers.
     */
    public static void setFunnelShape(int[] shape) throws IllegalArgumentException {
        for (int rowSize : shape) {
            if (rowSize % 2 == 0) {
                throw new IllegalArgumentException("Funnel shape must be an array of odd numbers.");
            }

            if (rowSize > bulbWidth) {
                throw new IllegalArgumentException("Funnel shape must not exceed bulb width.");
            }
        }

        if (shape.length > bulbHeight) {
            throw new IllegalArgumentException("Funnel shape must not exceed bulb height.");
        }

        funnelShape = shape;
    }

    /**
     * Get the total sand in the MinuteGlass.
     */
    public static int getTotalSand() {
        return totalSand;
    }

    /**
     * Set bulb dimensions.
     *
     * @param height The height of the bulb, i.e. the number of rows.
     * @param width  The width of the bulb, i.e. the number of granules per row.
     */
    public static void setBulbDimensions(int height, int width) {
        bulbHeight = height;
        bulbWidth = width;
    }

    /**
     * Get bulb dimensions.
     *
     * @return An array containing the width and height of the bulb, in that order.
     */
    public static int[] getBulbDimensions() {
        return new int[] { bulbWidth, bulbHeight };
    }

    /**
     * Create a bulb where the rows get smaller near the bottom so that the funnel
     * tightens near the bottom.
     */
    private void createTopBulb() {
        for (int i = 0; i < Bulb.bulbHeight; i++) {
            int rowCapacity;

            if (i > Bulb.bulbHeight - funnelShape.length - 1) {
                rowCapacity = funnelShape[i + funnelShape.length - Bulb.bulbHeight];

                this.add(new Row(rowCapacity, Granule.State.FILLED));

                Bulb.totalSand += rowCapacity;

                continue;
            }

            rowCapacity = Bulb.bulbWidth;

            this.add(new Row(rowCapacity, Granule.State.FILLED));

            Bulb.totalSand += rowCapacity;
        }
    }

    /**
     * Create a bulb where the rows get bigger from the top so that the funnel
     * tightens near the top.
     */
    private void createBottomBulb() {
        for (int i = 0; i < Bulb.bulbHeight; i++) {
            int rowCapacity;

            if (i < funnelShape.length) {
                rowCapacity = funnelShape[funnelShape.length - i - 1];

                Row emptyRow = new Row(rowCapacity, Granule.State.EMPTY);
                this.add(emptyRow);

                continue;
            }

            rowCapacity = bulbWidth;

            Row emptyRow = new Row(rowCapacity, Granule.State.EMPTY);
            this.add(emptyRow);
        }
    }
}
