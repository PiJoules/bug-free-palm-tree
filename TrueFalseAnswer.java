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

    @Override
    public String toString(){
        return Boolean.toString(choice);
    }

    @Override
    public boolean equals(Object other){
        if (!(other instanceof TrueFalseAnswer)){
            return false;
        }

        return choice == ((TrueFalseAnswer)other).getChoice();
    }
}
