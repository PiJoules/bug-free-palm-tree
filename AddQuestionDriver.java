import java.util.ArrayList;
import java.util.Scanner;

public abstract class AddQuestionDriver extends Driver {
    protected final int MAX_ENTER_COUNT = 2;

    protected abstract String questionName();

    public void showPrompt(){
        System.out.println("Enter the prompt for your " + questionName() + " question:");
        System.out.println("You can enter a single newline by pressing enter once.");
        System.out.println("Press enter twice in a row to finish entering the question.");
    }

    /**
     * Keep reading lines until enter is pressed N times in a row.
     */
    protected ArrayList<String> readMultipleLines(Scanner scanner){
        ArrayList<String> lines = new ArrayList<>();
        int enters = 0;
        while (enters < MAX_ENTER_COUNT){
            String next = scanner.nextLine();
            if (next.isEmpty()){
                enters++;
            }
            else {
                lines.add(next);
                enters = 1;
            }
        }
        return lines;
    }

    /**
     * Read multiple lines into 1 string.
     */
    protected String readMultiLineString(Scanner scanner, String delimiter){
        StringBuilder sb = new StringBuilder();
        for (String line : readMultipleLines(scanner)){
            sb.append(line);
            sb.append(delimiter);
        }
        // Trim last delimiter
        if (sb.length() >= delimiter.length()){
            sb.delete(sb.length()-delimiter.length(), sb.length());
        }
        return sb.toString();
    }
    protected String readMultiLineString(Scanner scanner){
        return readMultiLineString(scanner, "\n");
    }

    /**
     * Validating input
     */
    protected boolean isValidAnswer(String text){
        return !text.equalsIgnoreCase("");
    }
    protected boolean isValidAnswers(ArrayList<String> texts){
        for (String text : texts){
            if (!isValidAnswer(text)){
                return false;
            }
        }
        return true;
    }
}
