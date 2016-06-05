import java.util.Scanner;
import java.util.ArrayList;

public class AddRankChoicesQuestion extends AddQuestionDriver {
    protected String questionName(){
        return "ranking choices";
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
        RankChoicesAnswer answer;
        boolean gradeable = false;
        if (questionnaire instanceof Test){
            ArrayList<String> entries = new ArrayList<>();
            do {
                VoiceStream.println("Enter the correct ranking one line at a time. You must provide at least one.");
                VoiceStream.println("Press enter twice in a row to finish entering the question.");
                entries = readMultipleLines(scanner);
            } while (entries.isEmpty() || !isValidAnswers(entries));
            answer = new RankChoicesAnswer(entries);
            gradeable = true;
        }
        else {
            answer = new RankChoicesAnswer();
        }
        answers.add(answer);

        // Create question
        Question question = new Question(questionText, answers, gradeable);

        // Add question
        questionnaire.addQuestion(question);

        return new Menu2Driver();
    }
}
