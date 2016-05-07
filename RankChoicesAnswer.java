import java.util.ArrayList;
import org.json.*;  // json-20160212.jar

public class RankChoicesAnswer extends MultipleChoiceAnswer {
    public RankChoicesAnswer(){
        super();
    }
    public RankChoicesAnswer(ArrayList<String> choices){
        super(choices);
    }
    public RankChoicesAnswer(JSONArray answerObj) throws JSONException {
        super(answerObj);
    }
}
