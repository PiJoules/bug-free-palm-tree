import java.util.Scanner;
import java.util.ArrayList;

public class AddMultipleChoiceQuestion extends AddQuestionDriver {
    private final int MAX_ENTER_COUNT = 2;

    protected final String questionName(){
        return "Multiple Choice";
    }

    @Override
    public void showPrompt(){
        System.out.println("Enter the prompt for your multiple choice question.");
        System.out.println("You can enter a single newline by pressing enter once.");
        System.out.println("Press enter twice in a row to finish entering the question.");
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
        };

        // Get answer if Test
        Questionnaire questionnaire = (Questionnaire)args[0];
        ArrayList<Answer> answers = new ArrayList<>();
        MultipleChoiceAnswer answer;
        boolean gradeable = false;
        if (questionnaire instanceof Test){
            ArrayList<String> entries = new ArrayList<>();
            do {
                System.out.println("Enter your valid choices. You must provide at least one:");
                entries = readMultipleLines(scanner);
            } while (entries.isEmpty() || !isValidAnswers(entries));
            answer = new MultipleChoiceAnswer(entries);
            gradeable = true;
        }
        else {
            answer = new MultipleChoiceAnswer();
        }
        answers.add(answer);

        // Create question
        Question question = new Question(questionText, answers, gradeable);

        // Add question
        questionnaire.addQuestion(question);

        return new Menu2Driver();
    }

    protected boolean isValidAnswer(String text){
        return !text.equalsIgnoreCase("");
    }
    protected boolean isValidAnswers(ArrayList<String> texts){
        for (String text : texts){
            if (!isValidAnswer(text)){
                return false;
            }
        }
        return true;
    }
}
