package view;

import javax.swing.*;
import java.awt.*;

/**
 * Primary window class for the application, this keeps track of a single
 * timer.
 */
public class Glass extends JFrame {
    /**
     * The upper bulb of the MinuteGlass.
     */
    private Bulb topBulb = new Bulb(Bulb.Position.TOP);

    /**
     * The lower bulb of the MinuteGlass.
     */
    private Bulb bottomBulb = new Bulb(Bulb.Position.BOTTOM);

    /**
     * Constructor for the Glass class. Creates a window with the theme's background
     * color, sized at 500x500 pixels.
     */
    public Glass() {
        this.setSize(250, 500);
        this.setResizable(false);
        this.setLocation(40, 40);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setName("MinuteGlass");
        this.setVisible(true);

        JPanel center = new JPanel();
        center.setLayout(new GridLayout(2, 1, 0, 0));
        center.add(topBulb);
        center.add(bottomBulb);
        center.setBackground(Theme.BACKGROUND);
        center.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.add(center, BorderLayout.CENTER);
    }

    /**
     * Moves a sand granule from the top bulb to the bottom bulb.
     */
    public void moveGranule() {
        topBulb.removeGranule();
        bottomBulb.addGranule();
    }

    /**
     * Method for resetting the state of the MinuteGlass to empty.
     */
    public void reset() {
        bottomBulb.empty();
        topBulb.fill();
    }
}
