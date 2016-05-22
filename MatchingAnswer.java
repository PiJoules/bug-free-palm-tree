import java.util.ArrayList;
import java.util.Arrays;
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
    public MatchingAnswer(String answerStr, String delimiter){
        super(new ArrayList<String>(Arrays.asList(answerStr.split(delimiter))));
    }

    @Override
    public boolean equals(Object other){
        if (!(other instanceof MatchingAnswer)){
            return false;
        }
        ArrayList<String> choices = getChoices();
        ArrayList<String> choices2 = ((MatchingAnswer)other).getChoices();
        return choices.size() == choices2.size() && choices.containsAll(choices2) && choices2.containsAll(choices);
    }
}
