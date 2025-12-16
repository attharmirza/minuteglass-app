import model.Timer;
import view.Glass;
import controller.AnimationController;

/**
 * Main class for the MinuteGlass application. It's where the whole thing comes
 * together.
 */
public class Main {
    public static void main(String[] args) {
        Glass myGlass = new Glass();
        Timer myTimer = new Timer(10, 0);

        AnimationController controller = new AnimationController(myGlass, myTimer);
        controller.startAnimation();
    }
}
