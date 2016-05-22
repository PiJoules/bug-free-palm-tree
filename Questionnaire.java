import java.util.ArrayList;
import org.json.*;  // json-20160212.jar
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.io.IOException;
import java.io.PrintWriter;

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
     * Save this questionnaire to a file.
     */
    public void save(String filename) throws IOException {
        JSONArray jsonQuestions = new JSONArray();
        for (Question question : questions){
            jsonQuestions.put(question.toJSON());
        }
        PrintWriter pw = new PrintWriter(filename);
        pw.print(jsonQuestions.toString());
        pw.close();
    }

    /**
     * Add a new question.
     */
    public void addQuestion(Question question){
        questions.add(question);
    }
    public void replaceQuestion(int i, Question question){
        questions.set(i, question);
    }

    /**
     * Display the questionnaire in a readble format.
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < questions.size(); i++){
            Question question = questions.get(i);
            sb.append(String.format("Question %d. %s\n\n", i + 1, question.getText()));
        }
        return sb.toString();
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
