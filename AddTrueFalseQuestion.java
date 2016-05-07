import java.util.Scanner;

public class AddTrueFalseQuestion extends AddQuestionDriver {
    protected final String questionName(){
        return "True/False";
    }

    public Driver handleInput(Scanner scanner, Object... args){
        String entry = scanner.nextLine();
        return this;
    }
}
