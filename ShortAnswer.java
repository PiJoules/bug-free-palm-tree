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
}
