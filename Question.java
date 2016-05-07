public abstract class Question {
    private final String text;
    private final Answer answer;  // Expected answer

    /**
     * Question constructors.
     * Properties are set on construction.
     */
    public Question(String text, Answer answer){
        this.text = text;
        this.answer = answer;
    }
    public Question(String text){
        this(text, null);
    }
}
