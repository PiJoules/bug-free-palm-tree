import org.json.*;  // json-20160212.jar
import java.util.ArrayList;

public class Question {
    // Constants
    private final String QUESTION_TEXT_KEY = "questionText";
    private final String ANSWER_TYPE_KEY = "answerType";
    private final String EXPECTED_ANSWER_KEY = "expectedAnswers";
    // Question types
    private final String TRUE_FALSE = "TrueFalse";
    private final String MULTIPLE_CHOICE = "MultipleChoice";
    private final String SHORT = "Short";
    private final String ESSAY = "Essay";
    private final String RANK_CHOICES = "RankChoices";
    private final String MATCHING = "Matching";

    private final String text;
    private final ArrayList<Answer> answers;  // Expected answer(s)
    private final boolean gradable;

    /**
     * Question constructors.
     * Properties are set on construction.
     */
    public Question(String text, ArrayList<Answer> answers, boolean gradable){
        this.text = text;
        this.gradable = gradable;
        this.answers = answers;
    }
    public Question(String text, ArrayList<Answer> answers){
        this(text, answers, false);
    }
    public Question(JSONObject questionJson) throws Exception {
        String answerType = questionJson.getString(ANSWER_TYPE_KEY);
        ArrayList<Answer> answers_ = new ArrayList<>();

        // Remarkably, the JDK7 release allows for using Strings, an
        // Object, in switch statements as you would normally expect.
        if (!questionJson.has(EXPECTED_ANSWER_KEY)){
            Answer answer = null;
            switch (answerType){
                case TRUE_FALSE:
                    answer = new TrueFalseAnswer();
                    break;
                case MULTIPLE_CHOICE:
                    answer = new MultipleChoiceAnswer();
                    break;
                case SHORT:
                    // Short answers can be graded if given a string,
                    // or cannot be graded if null is provided.
                    answer = new ShortAnswer();
                    break;
                case ESSAY:
                    // Essays cannot be graded
                    answer = new EssayAnswer();
                    break;
                case RANK_CHOICES:
                    answer = new RankChoicesAnswer();
                    break;
                case MATCHING:
                    answer = new MatchingAnswer();
                    break;
                default:
                    throw new Exception("Unknown answerType " + answerType);
            }
            answers_.add(answer);
            gradable = false;
        }
        else {
            JSONArray expectedAnswers = questionJson.getJSONArray(EXPECTED_ANSWER_KEY);
            for (int i = 0; i < expectedAnswers.length(); i++){
                Answer answer = null;
                switch (answerType){
                    case TRUE_FALSE:
                        answer = new TrueFalseAnswer(expectedAnswers.getBoolean(i));
                        break;
                    case MULTIPLE_CHOICE:
                        answer = new MultipleChoiceAnswer(expectedAnswers.getJSONArray(i));
                        break;
                    case SHORT:
                        // Short answers can be graded if given a string,
                        // or cannot be graded if null is provided.
                        answer = new ShortAnswer(expectedAnswers.getString(i));
                        break;
                    case ESSAY:
                        // Essays cannot be graded
                        answer = new EssayAnswer();
                        break;
                    case RANK_CHOICES:
                        answer = new RankChoicesAnswer(expectedAnswers.getJSONArray(i));
                        break;
                    case MATCHING:
                        answer = new MatchingAnswer(expectedAnswers.getJSONArray(i));
                        break;
                    default:
                        throw new Exception("Unknown answerType " + answerType);
                }
                answers_.add(answer);
            }
            gradable = true;
        }

        // Set properties
        this.text = questionJson.getString(QUESTION_TEXT_KEY);
        this.answers = answers_;
    }

    /**
     * Getters
     */
    public final String getText(){
        return text;
    }
    public final ArrayList<Answer> getAnswers(){
        return answers;
    }
    public final boolean isGradable(){
        return gradable;
    }
}
