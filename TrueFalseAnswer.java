public class TrueFalseAnswer extends Answer {
    protected boolean choice = false;

    /**
     * Constructors
     */
    public TrueFalseAnswer(){}
    public TrueFalseAnswer(boolean choice){
        this.choice = choice;
    }

    public final boolean getChoice(){
        return choice;
    }
}
