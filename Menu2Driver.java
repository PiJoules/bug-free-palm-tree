import java.util.Scanner;

public class Menu2Driver extends MenuDriver {
    protected final String[] options(){
        return new String[]{
            "1) Add a new T/F question",
            "2) Add a new multiple choice question",
            "3) Add a new short answer question",
            "4) Add a new essay question",
            "5) Add a new ranking question",
            "6) Add a new matching question",
            "7) Go back"
        };
    }

    protected final String header(){
        return "Menu 2";
    }

    public Driver handleInput(Scanner scanner, Object... args){
        Integer selection_ = selection(scanner);
        if (selection_ == null){
            return this;
        }

        // Handle valid input
        switch (selection_){
            case 1:
                return new AddTrueFalseQuestion();
            case 2:
                return new AddMultipleChoiceQuestion();
            case 3:
                return new AddShortQuestion();
            case 4:
                return new AddEssayQuestion();
            case 5:
                return new AddRankChoicesQuestion();
            case 6:
                return new AddMatchingQuestion();
            case 7:
                return new Menu1Driver();
        }

        // Still on this menu
        return this;
    }

}
