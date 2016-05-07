import java.util.Scanner;
import java.util.HashMap;

public class MainDriver {
    public static void main(String[] args){
        Driver driver = new Menu1Driver();
        Scanner scanner = new Scanner(System.in);
        Questionnaire questionnaire = null;

        while (true){
            driver.showPrompt();
            System.out.print("> ");

            // Get next driver from user input
            Driver nextDriver = driver.handleInput(scanner);

            // Handle updated driver
            if (driver instanceof Menu1Driver){
                Menu1Driver menu1Driver = (Menu1Driver)driver;
                if (menu1Driver.shouldExit()){
                    break;
                }

                // Load any new questionnaires
                if (menu1Driver.getSurvey() != null){
                    questionnaire = menu1Driver.getSurvey();
                }
                else if (menu1Driver.getTest() != null){
                    questionnaire = menu1Driver.getTest();
                }
            }

            // Change to the new driver
            driver = nextDriver;

            System.out.println("");
        }
    }
}
