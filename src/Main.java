import model.Timer;
import view.Glass;
import controller.AnimationController;

/**
 * Main class for the MinuteGlass application. It's where the whole thing comes
 * together.
 *
 * @author Atthar Mirza
 * @version 0.1.0
 */
public class Main {
    /**
     * Storing the arguments as an instance var
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            validateArgs(args);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        int[] parsedArgs = parseArgs(args);

        Glass myGlass = new Glass();
        Timer myTimer;

        switch (parsedArgs.length) {
            case 3:
                myTimer = new Timer(parsedArgs[0], parsedArgs[1], parsedArgs[2]);
                break;
            case 2:
                myTimer = new Timer(parsedArgs[0], parsedArgs[1]);
                break;
            case 1:
                myTimer = new Timer(parsedArgs[0]);
                break;
            default:
                myTimer = new Timer();
        }

        AnimationController controller = new AnimationController(myGlass, myTimer);
        controller.startAnimation();
    }

    /**
     * Validate the input arguments into the main method.
     *
     * @throws IllegalArgumentException if the input is invalid
     */
    private static void validateArgs(String[] args) throws IllegalArgumentException {
        if (args.length > 3) {
            throw new IllegalArgumentException("Can have at most 3 arguments: minutes, seconds, and milliseconds.");
        }

        for (String arg : args) {
            try {
                Integer.parseInt(arg);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid input, each argument must be an integer");
            }
        }
    }

    /**
     * Convert the input arguments into an array of integers. If all three are
     * provided, the array is of the shape: [minutes, seconds, milliseconds]
     *
     * @param args
     * @return
     */
    private static int[] parseArgs(String[] args) {
        int[] parsedArgs = new int[args.length];

        for (int i = 0; i < args.length; i++) {
            parsedArgs[i] = Integer.parseInt(args[i]);
        }

        return parsedArgs;
    }

}
