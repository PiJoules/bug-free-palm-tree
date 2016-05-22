import java.util.ArrayList;
import java.util.Arrays;
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
    public RankChoicesAnswer(String answerStr, String delimiter){
        super(new ArrayList<String>(Arrays.asList(answerStr.split(delimiter))));
    }

    @Override
    public boolean equals(Object other){
        if (!(other instanceof RankChoicesAnswer)){
            return false;
        }
        return getChoices().equals(((RankChoicesAnswer)other).getChoices());
    }
}
