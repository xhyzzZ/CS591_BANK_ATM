package atm;

import atm.gui.GUI;
import static atm.util.Constants.INVALID_COMMAND_ARGS_CODE;

public final class Main {

    /**
     * Don't let anyone instantiate this class.
     */
    private Main() {}

    /**
     * Main entry point for the program.
     *
     * @param args
     *        Command line arguments.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            new GUI();
        } else {
            System.out.println("Invalid command line arguments.");
            System.exit(INVALID_COMMAND_ARGS_CODE);
        }
    }

}
