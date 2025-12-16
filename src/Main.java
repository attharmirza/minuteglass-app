import view.Glass;

/**
 * Main class for the MinuteGlass application. It's where the whole thing comes
 * together.
 */
public class Main {
    public static void main(String[] args) {
        Glass MinuteGlass = new Glass();
        MinuteGlass.setVisible(true);
        MinuteGlass.startAnimation();
    }
}
