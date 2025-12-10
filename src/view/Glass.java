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
    private int bulbRows = 20;

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
        center.add(new RowContainer(bulbRows));
        center.setBackground(Theme.BACKGROUND_COLOR);

        this.add(center, BorderLayout.CENTER);
    }

    /**
     * A container for rows of bulbs, lives centered in the window.
     */
    class RowContainer extends JPanel {
        public RowContainer(int bulbRows) {
            this.setPreferredSize(new Dimension(200, 400));
            this.setLayout(new GridLayout(bulbRows * 2, 1, 0, 5));
            this.setBackground(Theme.BACKGROUND_COLOR);

            for (int i = 0; i < bulbRows; i++) {
                this.add(new Row(bulbRows));
            }

            for (int i = 0; i < bulbRows; i++) {
                this.add(new Row(bulbRows));
            }
        }
    }
}
