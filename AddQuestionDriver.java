public abstract class AddQuestionDriver extends Driver {
    protected abstract String questionName();

    public void showPrompt(){
        System.out.println("Enter the prompt for your " + questionName() + " question:");
    }
}
