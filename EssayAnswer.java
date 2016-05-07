/**
 * Open ended essay answer that cannot be graded.
 */
public class EssayAnswer extends Answer {
    protected String text;

    public EssayAnswer(){
        this(null);
    }
    public EssayAnswer(String text){
        this.text = text;
    }

    public final String getText(){
        return text;
    }

    @Override
    public String toString(){
        return text;
    }
}
