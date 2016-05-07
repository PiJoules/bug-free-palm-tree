import java.util.Scanner;

/**
 * Class that will handle taking a string input from the command line,
 * checking the input, and returning an appropriate response back to the
 * main driver.
 */
public abstract class Driver {
    /**
     * Prompt to show in command line.
     */
    public abstract void showPrompt();

    /**
     * Handle input from the Scanner appropriately.
     * Optional arguments are also acceptable by this function.
     * Returns the next driver to use.
     */
    public abstract Driver handleInput(Scanner scan, Object... args);
}
