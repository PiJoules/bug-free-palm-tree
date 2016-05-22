/**
 * Short answer that is different from an essay answer in that this can be graded
 * if the provided string is not null.
 */
public class ShortAnswer extends EssayAnswer {
    public ShortAnswer(){
        super(null);
    }
    public ShortAnswer(String text){
        super(text);
    }

    @Override
    public boolean equals(Object other){
        if (!(other instanceof ShortAnswer)){
            return false;
        }

        return getText().equals(((ShortAnswer)other).getText());
    }
}
