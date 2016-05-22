import java.util.Scanner;
import java.util.ArrayList;

public class TabulateQuestionnaireDriver extends Driver {
    public void showPrompt(){}

    private int factorial(int n) {
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }

    @Override
    public Driver handleInput(Scanner scan, Object... args){
        if (args[0] != null){
            try {
                // Test to grade against
                Questionnaire questionnaire = (Questionnaire)args[0];
                
                // Display statistics
                int tfCount = 0;
                int mcQuestions = 0;
                int shortQuestions = 0;
                int essayQuestions = 0;
                int rankingQuestions = 0;
                int matchingQuestions = 0;
                for (Question question : questionnaire.getQuestions()){
                    ArrayList<Answer> answers = question.getAnswers();
                    switch (question.type()){
                        case TRUE_FALSE:
                            tfCount++;
                            break;
                        case MULTIPLE_CHOICE:
                            MultipleChoiceAnswer firstMC = (MultipleChoiceAnswer)answers.get(0);
                            System.out.println(question.getText());
                            System.out.println(firstMC.getChoices().size() + " responses");
                            mcQuestions++;
                            break;
                        case SHORT:
                            System.out.println(question.getText());
                            System.out.println(question.getAnswers().size() + " responses");
                            shortQuestions++;
                            break;
                        case ESSAY:
                            essayQuestions++;
                            break;
                        case RANK_CHOICES:
                            RankChoicesAnswer firstRC = (RankChoicesAnswer)answers.get(0);
                            System.out.println(question.getText());
                            System.out.println(factorial(firstRC.getChoices().size()) + " permutations");
                            rankingQuestions++;
                            break;
                        case MATCHING:
                            MatchingAnswer firstM = (MatchingAnswer)answers.get(0);
                            System.out.println(question.getText());
                            System.out.println(factorial(firstM.getChoices().size()) + " permutations");
                            matchingQuestions++;
                            break;
                        default:
                            throw new RuntimeException("Unknown question type: " + question.type().toString());
                    }
                }
                System.out.println("True/False Questions: " + tfCount);
                System.out.println("Multiple Choice Questions: " + mcQuestions);
                System.out.println("Short Answer Questions: " + shortQuestions);
                System.out.println("Essay Questions: " + essayQuestions);
                System.out.println("Matching Questions: " + matchingQuestions);
                System.out.println("Ranking Questions: " + rankingQuestions);
            }
            catch (Exception e){
                System.err.println("Could not load/grade answers: " + e.getMessage());
            }
        }
        else {
            System.err.println("Could not tabulate questionnaire since none was loaded or created prior.");
        }

        return new Menu1Driver();
    }
}
