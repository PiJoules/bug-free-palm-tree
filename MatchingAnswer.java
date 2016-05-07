import java.util.ArrayList;
import org.json.*;  // json-20160212.jar

public class MatchingAnswer extends MultipleChoiceAnswer {
    public MatchingAnswer(){
        super();
    }
    public MatchingAnswer(ArrayList<String> choices){
        super(choices);
    }
    public MatchingAnswer(JSONArray answerObj) throws JSONException {
        super(answerObj);
    }
}
