import java.util.ArrayList;

public class TrueFalseAnswer extends MultipleChoiceAnswer {
    /**
     * Constructors
     */
    public TrueFalseAnswer(){
        super();
        ArrayList<String> choices = new ArrayList<>();
        choices.add("True");
        choices.add("False");
        this.choices = choices;
    }
}
