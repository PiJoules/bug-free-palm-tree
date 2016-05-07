public class Test extends Questionnaire {
    public Test(){
        super();
    }
    public Test(String filename) throws Exception {
        super(filename);

        // Check that all questions that don't have
        // an essay answer are gradeable
        for (Question question : questions){
            Answer answer = question.getAnswers().get(0);
            if (!(answer.getClass().equals(EssayAnswer.class)) && !question.isGradable()){
                throw new Exception("All questions in a test must be gradeable.");
            }
        }
    }

    /**
     * Display the questionnaire in a readble format.
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < questions.size(); i++){
            Question question = questions.get(i);
            sb.append(String.format("Question %d. %s\n\n", i + 1, question.getText()));
            sb.append(String.format("Answer(s) to %d:\n", i + 1));
            for (Answer answer : question.getAnswers()){
                sb.append(String.format("%s\n", answer.toString()));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
