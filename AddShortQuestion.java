import java.util.Scanner;
import java.util.ArrayList;

public class AddShortQuestion extends AddMultipleChoiceQuestion {
    protected String questionName(){
        return "short answer";
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
        ShortAnswer answer;
        boolean gradeable = false;
        if (questionnaire instanceof Test){
            ArrayList<String> entries = new ArrayList<>();
            do {
                VoiceStream.println("Enter the correct short answers one line at a time. You must provide at least one.");
                VoiceStream.println("Press enter twice in a row to finish entering the question.");
                entries = readMultipleLines(scanner);
            } while (entries.isEmpty() || !isValidAnswers(entries));
            for (String entry : entries){
                answers.add(new ShortAnswer(entry));
            }
            gradeable = true;
        }
        else {
            answers.add(new ShortAnswer());
        }

        // Create question
        Question question = new Question(questionText, answers, gradeable);

        // Add question
        questionnaire.addQuestion(question);

        return new Menu2Driver();
    }
}
