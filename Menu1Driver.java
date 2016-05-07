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
            "9) Quit"
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
            case 9:
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
