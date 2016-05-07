import java.util.Scanner;
import java.io.File;

public class MainDriver {
    public static void main(String[] args) throws Exception{
        Driver driver = new Menu1Driver();
        Scanner scanner;
        if (args.length < 1){
            scanner = new Scanner(System.in);
        }
        else {
            scanner = new Scanner(new File(args[0]));
        }
        Test test = null;
        Survey survey = null;

        while (true){
            driver.showPrompt();

            // Get next driver from user input
            Driver nextDriver;
            if (driver instanceof DisplaySurveyDriver){
                nextDriver = driver.handleInput(null, survey);
            }
            else if (driver instanceof DisplayTestDriver){
                nextDriver = driver.handleInput(null, test);
            }
            else if (driver instanceof SaveSurveyDriver){
                nextDriver = driver.handleInput(scanner, survey);
            }
            else if (driver instanceof SaveTestDriver){
                nextDriver = driver.handleInput(scanner, test);
            }
            else {
                System.out.print("> ");  // Print new shell marker
                nextDriver = driver.handleInput(scanner);
            }

            // Handle updated driver
            if (driver instanceof Menu1Driver){
                Menu1Driver menu1Driver = (Menu1Driver)driver;
                if (menu1Driver.shouldExit()){
                    break;
                }

                // Load any new questionnaires
                if (menu1Driver.getSurvey() != null){
                    survey = menu1Driver.getSurvey();
                }
                else if (menu1Driver.getTest() != null){
                    test = menu1Driver.getTest();
                }
            }
            else if (driver instanceof LoadSurveyDriver){
                LoadSurveyDriver loadSurveyDriver = (LoadSurveyDriver)driver;
                survey = loadSurveyDriver.getSurvey();
            }
            else if (driver instanceof LoadTestDriver){
                LoadTestDriver loadTestDriver = (LoadTestDriver)driver;
                test = loadTestDriver.getTest();
            }

            // Change to the new driver
            driver = nextDriver;

            System.out.println("");
        }
    }
}
