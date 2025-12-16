package view;

import javax.swing.*;
import java.awt.*;

/**
 * Primary window class for the application, this keeps track of a single
 * timer.
 */
public class Glass extends JFrame {
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
        center.add(new Bulb(Bulb.Position.TOP));
        center.add(new Bulb(Bulb.Position.BOTTOM));
        center.setBackground(Theme.BACKGROUND);

        this.add(center, BorderLayout.CENTER);

        System.out.println("Total sand: " + Bulb.getTotalSand());
    }
}
