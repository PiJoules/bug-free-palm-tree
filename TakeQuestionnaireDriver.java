import java.util.Scanner;
import java.util.ArrayList;
import org.json.*;  // json-20160212.jar
import java.io.IOException;
import java.io.PrintWriter;

public class TakeQuestionnaireDriver<T extends Questionnaire> extends Driver {
    public void showPrompt(){}

    /**
     * Validating input
     */
    protected boolean isValidTrueFalseAnswer(String text){
        boolean isValid = text.equalsIgnoreCase("false") || text.equalsIgnoreCase("true");
        if (!isValid){
            System.err.println("Invalid anwer. The answer can only be 'true' or 'false'.");
        }
        return isValid;
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

    @SuppressWarnings("unchecked")
    public Driver handleInput(Scanner scan, Object... args){
        if (args[0] != null){
            Questionnaire questionnaire = (T)args[0];
            if (questionnaire.getQuestions().size() == 0){
                System.err.println("This questionnaire has no questions and cannot be taken.");
                return new Menu1Driver();
            }

            VoiceStream.println("Enter the name of the file you wish to save these answers as:");
            String testName;
            while ((testName = scan.nextLine()).isEmpty());
            testName = testName.trim();

            ArrayList<Question> questions = questionnaire.getQuestions();
            ArrayList<String> answers = new ArrayList<>();
            for (int i = 0; i < questions.size(); i++){
                Question question = questions.get(i);
                VoiceStream.println((i + 1) + ") " +  question.getText());
                String answerText = null;
                switch (question.type()){
                    case TRUE_FALSE:
                    case MULTIPLE_CHOICE:
                        do {
                            while ((answerText = scan.nextLine()).isEmpty());
                        } while (answerText == null || !isValidAnswer(answerText));
                        break;
                    case SHORT:
                    case ESSAY:
                        do {
                            VoiceStream.println("You can enter an answer with multiple lines by pressing enter.");
                            VoiceStream.println("Press enter twice in a row to finish entering the question.");
                            answerText = Utils.readMultiLineString(scan);
                        } while (answerText == null || !isValidAnswer(answerText));
                        break;
                    case RANK_CHOICES:
                        do {
                            VoiceStream.println("Enter your answers separated by commas.");
                            VoiceStream.println("(Ex: A,B,C,D)");
                            while ((answerText = scan.nextLine()).isEmpty());
                        } while (answerText == null || !isValidAnswer(answerText));
                        break;
                    case MATCHING:
                        do {
                            VoiceStream.println("Enter your matches separated by commas, with each match separated by dashes.");
                            VoiceStream.println("(Ex: 1-A,2-B,3-C,4-D)");
                            while ((answerText = scan.nextLine()).isEmpty());
                        } while (answerText == null || !isValidAnswer(answerText));
                        break;
                    default:
                        throw new RuntimeException("Unknown question type: " + question.type().toString());
                }
                answers.add(answerText.trim());
                VoiceStream.println("");
            }
            saveAnswers(answers, testName);
        }
        else {
            System.err.println("Could not take questionnaire since none was loaded or created prior.");
        }
        return new Menu1Driver();
    }

    /**
     * Save list of answers into file.
     */
    private void saveAnswers(ArrayList<String> answers, String filename){
        try {
            JSONArray jsonAnswers = new JSONArray();
            for (String answer : answers){
                jsonAnswers.put(answer);
            }
            PrintWriter pw = new PrintWriter(filename);
            pw.print(jsonAnswers.toString());
            pw.close();
        }
        catch (IOException e){
            System.err.println("Could not save file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
