import java.util.Scanner;
import java.util.ArrayList;

public class AddEssayQuestion extends AddQuestionDriver {
    protected String questionName(){
        return "essay";
    }

    /**
     * Args:
     *      scanner (Scanner)
     *      questionnaire (Questionnaire)
     */
    public Driver handleInput(Scanner scanner, Object... args){
        // Get question text
        String questionText;
        while ((questionText = readMultiLineString(scanner)).isEmpty()){
            System.err.println("You must provide a question:");
        }

        // Get answer if Test
        Questionnaire questionnaire = (Questionnaire)args[0];
        ArrayList<Answer> answers = new ArrayList<>();
        EssayAnswer answer = new EssayAnswer();
        answers.add(answer);


        // Create question
        // Essays are not gradeable
        Question question = new Question(questionText, answers, false);

        // Add question
        questionnaire.addQuestion(question);

        return new Menu2Driver();
    }
}
