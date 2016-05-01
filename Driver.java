import java.util.Scanner;
import java.util.HashMap;

public class Driver {
    /**
     * Properties/attributes
     */
    private final String[] headers = new String[]{
        "Menu 1",
        "Menu 2"
    };

    private final String[][] menus = new String[][]{
        {
            "1) Create a new Survey",
            "2) Create a new Test",
            "3) Display a Survey",
            "4) Display a Test",
            "5) Load a Survey",
            "6) Load a Test",
            "7) Save a Survey",
            "8) Save a Test",
            "9) Quit"
        },
        {
            "1) Add a new T/F question",
            "2) Add a new multiple choice question",
            "3) Add a new short answer question",
            "4) Add a new essay question",
            "5) Add a new ranking question",
            "6) Add a new matching question",
            "7) Go back"
        }
    };

    private final Scanner scanner = new Scanner(System.in);
    private int currentMenu = 0;


    public static void main(String[] args){
        final Driver driver = new Driver();
        driver.run();
    }

    /**
     * Methods
     */
    public void run(){
        // Read from stdin
        while (true){
            // Show options
            printMenuOptions(currentMenu);

            if (!scanner.hasNextInt()){
                printInvalidEntry(scanner.next());
                continue;
            }
            int selection = scanner.nextInt();
            if (selection < 1 || selection > menus[currentMenu].length){
                printInvalidEntry(selection + "");
                continue;
            }

            System.out.println("");
            // --- Responses start here ---

            switch (currentMenu){
                case 0:
                    switch (selection){
                        case 1:
                            switchToMenu(1);
                            break;
                        case 9:
                            return;
                        default:
                            System.out.printf("Menu 1, selection %d\n", selection);
                    }
                    break;
                case 1:
                    switch (selection){
                        case 7:
                            switchToMenu(0);
                            break;
                        default:
                            System.out.printf("Menu 1, selection %d\n", selection);
                    }
                    break;
                default:
                    System.out.println(selection);
            }

            // --- Responses end here ---
            System.out.println("");
        }
    }

    /**
     * Print out menu header and options
     */
    private void printMenuOptions(int menu){
        System.out.println(headers[menu]);
        for (String option : menus[menu]){
            System.out.println(option);
        }
        System.out.print("> ");
    }


    /**
     * Error message
     */
    private void printInvalidEntry(String entry){
        System.out.println("");
        System.out.println("Invalid entry: " + entry);
        System.out.printf("Valid entries are: 1 - %d\n", menus[currentMenu].length);
        System.out.println("");
    }
    
    /**
     * Functions to call on menu selection.
     */
    public void switchToMenu(int menu){
        currentMenu = menu;
    }
}
