import java.util.Scanner;

public class LoadSurveyDriver extends Driver {
    private Survey survey = null;

    public void showPrompt(){
        System.out.println("Enter filename of survey to load:");
    }

    public Driver handleInput(Scanner scan, Object... args){
        String filename = scan.next();
        try {
            survey = new Survey(filename);
            System.out.println("Successfully loaded " + filename);
        }
        catch (Exception e){
            System.err.printf("Could not load from file '%s': %s\n", filename, e.getMessage());
        }
        return new Menu1Driver();
    }

    public Survey getSurvey(){
        return survey;
    }
}
