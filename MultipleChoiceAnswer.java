import java.util.ArrayList;
import org.json.*;  // json-20160212.jar

/**
 * Essentially a container for user-entered answers.
 */
public class MultipleChoiceAnswer extends Answer {
    protected ArrayList<String> choices = new ArrayList<>();

    /**
     * Constructors
     */
    public MultipleChoiceAnswer(){
        this(new ArrayList<String>());
    }
    public MultipleChoiceAnswer(ArrayList<String> choices){
        this.choices = choices;
    }
    public MultipleChoiceAnswer(JSONArray answerObj) throws JSONException {
        choices.clear();
        for (int i = 0; i < answerObj.length(); i++){
            choices.add(answerObj.getString(i));
        }
    }

    /**
     * Getters
     */
    public final ArrayList<String> getChoices(){
        return choices;
    }

    /**
     * Convert choices to JSON.
     */
    public JSONArray toJSON(){
        return new JSONArray(choices);
    }
}
