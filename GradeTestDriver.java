import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import org.json.*;  // json-20160212.jar

public class GradeTestDriver extends Driver {
    public void showPrompt(){}

    private JSONArray answersFromFile(String filename) throws Exception {
        // Read entire file into memory for now
        byte[] encoded = Files.readAllBytes(Paths.get(filename));
        String jsonText = new String(encoded, Charset.defaultCharset());

        // Load json
        return new JSONArray(jsonText);
    }

    public Driver handleInput(Scanner scan, Object... args){
        if (args[0] != null){
            try {
                // Test to grade against
                Questionnaire test = (Test)args[0];
                if (test.getQuestions().size() == 0){
                    System.err.println("This test has no answers provided and cannot be graded.");
                    return new Menu1Driver();
                }

                // Create jsonarray from filename
                VoiceStream.print("Enter the filename of the answers you wish to grade: ");
                String filename;
                while ((filename = scan.nextLine()).isEmpty());
                filename = filename.trim();
                JSONArray answers = answersFromFile(filename);

                // Grading
                int gradeableAnswers = 0;
                int correctAnswers = 0;
                ArrayList<Question> questions = test.getQuestions();
                for (int i = 0; i < questions.size(); i++){
                    // Get quesiton
                    Question question = questions.get(i);

                    // Check if answer is provided
                    if (i >= answers.length()){
                        // No answers provided for remaining questions
                        if (question.type() != QuestionType.ESSAY){
                            gradeableAnswers++;
                        }
                        continue;
                    }

                    // Grade given answer
                    ArrayList<Answer> possible = question.getAnswers();
                    switch (question.type()){
                        case TRUE_FALSE:
                            Answer providedTFAnswer = new TrueFalseAnswer(Boolean.parseBoolean(answers.getString(i)));
                            if (possible.contains(providedTFAnswer)){
                                correctAnswers++;
                            }
                            gradeableAnswers++;
                            break;
                        case MULTIPLE_CHOICE:
                            String providedMCAnswer = answers.getString(i);
                            for (Answer mcAnswer : possible){
                                if (((MultipleChoiceAnswer)mcAnswer).getChoices().contains(providedMCAnswer)){
                                    correctAnswers++;
                                    break;
                                }
                            }
                            gradeableAnswers++;
                            break;
                        case SHORT:
                            Answer providedShortAnswer = new ShortAnswer(answers.getString(i));
                            if (possible.contains(providedShortAnswer)){
                                correctAnswers++;
                            }
                            gradeableAnswers++;
                            break;
                        case ESSAY:  // Not graded
                            break;
                        case RANK_CHOICES:
                            RankChoicesAnswer providedRCAnswer = new RankChoicesAnswer(answers.getString(i), ",");
                            if (possible.contains(providedRCAnswer)){
                                correctAnswers++;
                            }
                            gradeableAnswers++;
                            break;
                        case MATCHING:
                            MatchingAnswer providedMatchingAnswer = new MatchingAnswer(answers.getString(i), ",");
                            if (possible.contains(providedMatchingAnswer)){
                                correctAnswers++;
                            }
                            gradeableAnswers++;
                            break;
                        default:
                            throw new RuntimeException("Unknown question type: " + question.type().toString());
                    }
                }

                // Display results
                VoiceStream.printf("Grade: %%%f (%d/%d)\n", (float)correctAnswers / gradeableAnswers * 100, correctAnswers, gradeableAnswers);
            }
            catch (Exception e){
                System.err.println("Could not load/grade answers: " + e.getMessage());
            }
        }
        else {
            System.err.println("Could not grade test since none was loaded or created prior.");
        }

        return new Menu1Driver();
    }
}
