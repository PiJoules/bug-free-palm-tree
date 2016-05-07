import java.util.ArrayList;
import org.json.*;  // json-20160212.jar
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.Charset;

/**
 * Question container class.
 */
public class Questionnaire {
    // Predefined questions with/without answers
    protected ArrayList<Question> questions = new ArrayList<>();
    // User entered answers to the questions
    protected ArrayList<Answer> answers = new ArrayList<>();  

    /**
     * Constructors
     */
    public Questionnaire(){}
    public Questionnaire(String filename) throws Exception {
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
            questions.add(new Question(questionJson));
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
