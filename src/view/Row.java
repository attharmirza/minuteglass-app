package view;

import javax.swing.*;
import java.awt.*;

/**
 * A single row of "sand granules" in the MinuteGlass display.
 */
public class Row extends JPanel {
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
        this.setLayout(new GridLayout(1, size, 5, 0));
        this.setBackground(Theme.BACKGROUND_COLOR);
        this.setOpaque(true);

        for (int i = 0; i < size; i++) {
            Granule granule = new Granule();
            granule.setState(Granule.State.FILLED);
            this.add(granule);
        }
    }
}
