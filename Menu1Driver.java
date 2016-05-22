import java.util.Scanner;

public class Menu1Driver extends MenuDriver {
    /**
     * Driver properties
     */
    private boolean shouldExit_ = false;
    private Survey survey = null;
    private Test test = null;

    /**
     * Menu driver getters
     */
    protected final String[] options(){
        return new String[]{
            "1) Create a new Survey",
            "2) Create a new Test",
            "3) Display a Survey",
            "4) Display a Test",
            "5) Load a Survey",
            "6) Load a Test",
            "7) Save a Survey",
            "8) Save a Test",
            "9) Modify an Existing Survey",
            "10) Modify an Existing Test",
            "11) Take a Survey",
            "12) Take a Test",
            "13) Grade a Test",
            "14) Tabulate a Survey",
            "15) Tabulate a Test",
            "16) Quit"
        };
    }
    protected final String header(){
        return "Menu 1";
    }

    public Driver handleInput(Scanner scanner, Object... args){
        Integer selection_ = selection(scanner);
        if (selection_ == null){
            return this;
        }

        // Handle valid input
        switch (selection_){
            case 1:
                survey = new Survey();
                return new Menu2Driver();
            case 2:
                test = new Test();
                return new Menu2Driver();
            case 3:
                return new DisplaySurveyDriver();
            case 4:
                return new DisplayTestDriver();
            case 5:
                return new LoadSurveyDriver();
            case 6:
                return new LoadTestDriver();
            case 7:
                return new SaveSurveyDriver();
            case 8:
                return new SaveTestDriver();
            case 9:
                return new ModifySurveyDriver();
            case 10:
                return new ModifyTestDriver();
            case 11:
                return new TakeSurveyDriver();
            case 12:
                return new TakeTestDriver();
            case 13:
                return new GradeTestDriver();
            case 14:
                return new TabulateSurveyDriver();
            case 15:
                return new TabulateTestDriver();
            case 16:
                shouldExit_ = true;
                break;
        }

        // Still on this menu
        return this;
    }

    /**
     * Getters
     */
    public boolean shouldExit(){
        return shouldExit_;
    }
    public Survey getSurvey(){
        return survey;
    }
    public Test getTest(){
        return test;
    }
}
