import java.util.Scanner;
import java.util.ArrayList;

public class AddTrueFalseQuestion extends AddQuestionDriver {
    protected final String questionName(){
        return "True/False";
    }

    /**
     * Args:
     *      scanner (Scanner)
     *      questionnaire (Questionnaire)
     */
    public Driver handleInput(Scanner scanner, Object... args){
        // Get question text
        String questionText;
        while ((questionText = scanner.nextLine()).isEmpty());

        // Get answer if Test
        Questionnaire questionnaire = (Questionnaire)args[0];
        ArrayList<Answer> answers = new ArrayList<>();
        TrueFalseAnswer answer;
        boolean gradeable = false;
        if (questionnaire instanceof Test){
            String answerText = null;
            do {
                System.out.println("Enter the answer to this question (True/False):");
                while ((answerText = scanner.nextLine()).isEmpty());
            } while (answerText == null || !isValidAnswer(answerText));
            answer = new TrueFalseAnswer(Boolean.parseBoolean(answerText));
            gradeable = true;
        }
        else {
            answer = new TrueFalseAnswer();
        }
        answers.add(answer);

        // Create question
        Question question = new Question(questionText, answers, gradeable);

        // Add question
        questionnaire.addQuestion(question);

        return new Menu2Driver();
    }

    /**
     * Only accept true/false as valid anser
     */
    @Override
    protected boolean isValidAnswer(String text){
        boolean isValid = text.equalsIgnoreCase("false") || text.equalsIgnoreCase("true");
        if (!isValid){
            System.err.println("Invalid anwer. The answer can only be 'true' or 'false'.");
        }
        return isValid;
    }
}
