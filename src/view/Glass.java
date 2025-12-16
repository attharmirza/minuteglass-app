package view;

import javax.swing.*;
import java.awt.*;

/**
 * Primary window class for the application, this keeps track of a single
 * timer.
 */
public class Glass extends JFrame {
    /**
     * Number of rows of bulbs in the glass. Only defining one bulb because I want
     * it to always be an even number. This will get multiplied by 2 to get the
     * total number of rows.
     */
    private int bulbHeight = 20;

    /**
     * Number of granules in each row of the bulbs, should always be an odd number.
     */
    private int bulbWidth = 21;

    /**
     * An array of integers representing the shape of the MinuteGlass funnel. It
     * must be an array of odd numbers.
     */
    private int[] funnelShape = { 19, 15, 9, 5, 3, 1 };

    /**
     * Total amound of sand granules in the minuteglass.
     */
    private int totalSand = 0;

    /**
     * Constructor for the Glass class. Creates a window with the theme's background
     * color, sized at 500x500 pixels.
     */
    public Glass() {
        this.setSize(500, 500);
        this.setAlwaysOnTop(false);
        this.setLocation(20, 20);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        JPanel center = new JPanel();
        center.setLayout(new GridBagLayout());
        center.add(new RowContainer(bulbHeight));
        center.setBackground(Theme.BACKGROUND);

        this.add(center, BorderLayout.CENTER);

        System.out.println("Total sand: " + totalSand);
    }

    /**
     * A container for rows of bulbs, doing this basically just to always center the
     * hourglass.
     */
    class RowContainer extends JPanel {
        /**
         * Position of the funnel relative to the bulb.
         */
        private static enum Direction {
            BOTTOM, TOP
        }

        /**
         * Constructor for RowContainer.
         *
         * @param rows The number of rows in the bulb.
         */
        public RowContainer(int rows) {
            this.setPreferredSize(new Dimension(200, 400));
            this.setLayout(new GridLayout(rows * 2, 1, 0, 5));
            this.setBackground(Theme.BACKGROUND);

            try {
                createBulb(Direction.BOTTOM, rows);
                createBulb(Direction.TOP, rows);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();

                JLabel labelError = new JLabel("Could not initialize MinuteGlass.");

                this.add(labelError);
            }
        }

        /**
         * Creating a bulb with a funnel facing upward or downward.
         *
         * @param direction The direction of the funnel (UP or DOWN)
         * @param bulbRows  The number of rows in the bulb
         */
        private void createBulb(Direction direction, int bulbRows) throws IllegalArgumentException {
            for (int i = 0; i < bulbRows; i++) {
                int temp;

                if (direction == Direction.BOTTOM) {
                    if (i > bulbRows - funnelShape.length) {
                        temp = funnelShape[i + funnelShape.length - bulbRows - 1];

                        this.add(new Row(temp));

                        totalSand += temp;

                        continue;
                    }

                    temp = bulbWidth;

                    this.add(new Row(temp));

                    totalSand += temp;
                } else if (direction == Direction.TOP) {
                    if (i < funnelShape.length) {
                        temp = funnelShape[funnelShape.length - i - 1];

                        this.add(new Row(temp));

                        continue;
                    }

                    temp = bulbWidth;

                    this.add(new Row(temp));
                }
            }
        }
    }
}
