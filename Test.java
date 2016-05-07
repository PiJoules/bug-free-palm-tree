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
}
