import java.util.Scanner;
import java.util.ArrayList;

public class ModifyQuestionnaireDriver<T extends Questionnaire> extends Driver {
    public void showPrompt(){}

    private void printInvalidEntry(String entry, int numQuestions){
        System.out.println("Invalid entry: " + entry);
        System.out.printf("Valid entries are: 1 - %d\n", numQuestions);
    }
    private void printInvalidEntry(String entry){
        System.out.println("Invalid entry: " + entry);
        System.out.println("Valid entries are Y or N.");
    }

    /**
     * Return number selected.
     * Return null if invalid number and prints an error message.
     */
    protected Integer selection(Scanner scanner, int numQuestions){
        if (!scanner.hasNextInt()){
            printInvalidEntry(scanner.next(), numQuestions);
            return null;
        }
        int selection = scanner.nextInt();
        if (selection < 1 || selection > numQuestions){
            printInvalidEntry(selection + "", numQuestions);
            return null;
        }
        return selection;
    }

    protected Boolean selection(Scanner scanner){
        if (!scanner.hasNextBoolean()){
            printInvalidEntry(scanner.next());
            return null;
        }
        String selection = scanner.next();
        if (selection.equalsIgnoreCase("Y")){
            return true;
        }
        else if (selection.equalsIgnoreCase("N")){
            return false;
        }
        else {
            printInvalidEntry(selection);
            return null;
        }
    }

    /**
     * Only accept true/false as valid anser
     */
    protected boolean isValidTrueFalseAnswer(String text){
        boolean isValid = text.equalsIgnoreCase("false") || text.equalsIgnoreCase("true");
        if (!isValid){
            System.err.println("Invalid anwer. The answer can only be 'true' or 'false'.");
        }
        return isValid;
    }

    @SuppressWarnings("unchecked")
    public Driver handleInput(Scanner scan, Object... args){
        if (args[0] != null){
            Questionnaire questionnaire = (T)args[0];
            ArrayList<Question> questions = questionnaire.getQuestions();
            int numQuestions = questions.size();
            if (numQuestions == 0){
                System.err.println("Could not modify questionnaire since no questions have been added to the questionnaire.");
                return new Menu1Driver();
            }

            System.out.printf("Which question do you wish to modify (1-%d):", numQuestions);
            Integer selection_ = selection(scan, numQuestions);
            if (selection_ == null){
                return this;
            }

            // Change prompt
            Question question = questions.get(selection_ - 1);
            System.out.println("Prompt:");
            System.out.println(question.getText());
            System.out.print("Do you wish to modify the prompt? (Y/N):");
            boolean modifyPrompt = selection(scan);
            String questionText = question.getText();
            if (modifyPrompt){
                // Handle valid input
                switch (question.type()){
                    case TRUE_FALSE:
                        while ((questionText = scan.nextLine()).isEmpty());
                        break;
                    case MULTIPLE_CHOICE:
                    case SHORT:
                    case ESSAY:
                    case RANK_CHOICES:
                    case MATCHING:
                        Utils.showPrompt();
                        while ((questionText = Utils.readMultiLineString(scan)).isEmpty()){
                            System.err.println("You must provide a question:");
                        };
                        break;
                }
            }

            // Change answer
            ArrayList<Answer> answers = question.getAnswers();
            boolean gradeable = question.isGradable();
            if (questionnaire instanceof Test){
                System.out.println("Answer(s):");
                StringBuilder sb = new StringBuilder();
                for (Answer answer : question.getAnswers()){
                    sb.append(String.format("%s\n", answer.toString()));
                }
                System.out.println(sb);
                System.out.print("Do you wish to modify the correct answer(s)? (Y/N):");
                boolean modifyAnswer = selection(scan);
                if (modifyAnswer){
                    // Handle valid input
                    switch (question.type()){
                        case TRUE_FALSE:
                            String answerText = null;
                            do {
                                System.out.println("Enter the answer to this question (True/False):");
                                while ((answerText = scan.nextLine()).isEmpty());
                            } while (answerText == null || !isValidTrueFalseAnswer(answerText));
                            answer = new TrueFalseAnswer(Boolean.parseBoolean(answerText));
                            gradeable = true;
                            break;
                        case MULTIPLE_CHOICE:
                        case SHORT:
                        case ESSAY:
                        case RANK_CHOICES:
                        case MATCHING:
                            Utils.showPrompt();
                            while ((questionText = Utils.readMultiLineString(scan)).isEmpty()){
                                System.err.println("You must provide a question:");
                            };
                            break;
                    }
                }
            }
        }
        else {
            System.err.println("Could not edit questionnaire since none was loaded or created prior.");
        }

        return this;
    }
}
