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
        Questionnaire working = null;  // To discern if add question to test or survey

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
            else if (driver instanceof AddQuestionDriver){
                // Adding to new questionnaire
                nextDriver = driver.handleInput(scanner, working);
            }
            else if (driver instanceof ModifySurveyDriver){
                nextDriver = driver.handleInput(scanner, survey);
            }
            else if (driver instanceof ModifyTestDriver){
                nextDriver = driver.handleInput(scanner, test);
            }
            else if (driver instanceof TakeSurveyDriver){
                nextDriver = driver.handleInput(scanner, survey);
            }
            else if (driver instanceof TakeTestDriver){
                nextDriver = driver.handleInput(scanner, test);
            }
            else if (driver instanceof GradeTestDriver){
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
                    working = survey;
                }
                else if (menu1Driver.getTest() != null){
                    test = menu1Driver.getTest();
                    working = test;
                }
            }
            else if (driver instanceof LoadSurveyDriver){
                LoadSurveyDriver loadSurveyDriver = (LoadSurveyDriver)driver;
                survey = loadSurveyDriver.getSurvey();  // Set the new loaded survey
            }
            else if (driver instanceof LoadTestDriver){
                LoadTestDriver loadTestDriver = (LoadTestDriver)driver;
                test = loadTestDriver.getTest();  // Set the new loaded test
            }

            // Change to the new driver
            driver = nextDriver;

            System.out.println("");
        }
    }
}
