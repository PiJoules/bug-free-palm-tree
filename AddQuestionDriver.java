import java.util.ArrayList;
import java.util.Scanner;

public abstract class AddQuestionDriver extends Driver {
    protected final int MAX_ENTER_COUNT = 2;

    protected abstract String questionName();

    public void showPrompt(){
        System.out.println("Enter the prompt for your " + questionName() + " question:");
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
}
