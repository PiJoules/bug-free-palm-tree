import java.util.ArrayList;
import org.json.*;  // json-20160212.jar
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.Charset;

/**
 * Question container class.
 */
public class Questionnaire {
    // Constants
    private final String QUESTION_TEXT_KEY = "questionText";
    private final String ANSWER_TYPE_KEY = "answerType";
    private final String EXPECTED_ANSWER_KEY = "expectedAnswer";
    // Question types
    private final String TRUE_FALSE = "TrueFalse";
    private final String MULTIPLE_CHOICE = "MultipleChoice";
    private final String SHORT = "Short";
    private final String ESSAY = "Essay";
    private final String RANK_CHOICES = "RankChoices";
    private final String MATCHING = "Matching";

    // Predefined questions with/without answers
    private ArrayList<Question> questions = new ArrayList<>();
    // User entered answers to the questions
    private ArrayList<Answer> answers = new ArrayList<>();  

    /**
     * Constructors
     */
    public Questionnaire(){}
    public Questionnaire(String filename){
        try {
            // Read entire file into memory for now
            byte[] encoded = Files.readAllBytes(Paths.get(filename));
            String jsonText = new String(encoded, Charset.defaultCharset());

            // Load json
            JSONArray questionnaireJson = new JSONArray(jsonText);

            // Parse json
            // Create Question from JSONObject
            // The json must contain the questionText, answerType,
            // and expectedAnswer keys
            for (int i = 0; i < questionnaireJson.length(); i++){
                JSONObject questionJson = questionnaireJson.getJSONObject(i);
                String answerType = questionJson.getString(ANSWER_TYPE_KEY);
                Answer answer = null;

                // Remarkably, the JDK7 release allows for using Strings, an
                // Object, in switch statements as you would normally expect.
                switch (answerType){
                    case TRUE_FALSE:
                        answer = new TrueFalseAnswer(questionJson.getBoolean(EXPECTED_ANSWER_KEY));
                        break;
                    case MULTIPLE_CHOICE:
                        answer = new MultipleChoiceAnswer(questionJson.getJSONArray(EXPECTED_ANSWER_KEY));
                        break;
                    case SHORT:
                        break;
                    case ESSAY:
                        break;
                    case RANK_CHOICES:
                        break;
                    case MATCHING:
                        break;
                    default:
                        throw new Exception("Unknown answerType " + answerType);
                }
            }
        }
        catch (Exception e){
            System.err.printf("Could not load from file '%s': %s\n", filename, e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Getters
     */
    public ArrayList<Question> getQuestions(){
        return questions;
    }
    public ArrayList<Answer> getAnswers(){
        return answers;
    }
}
