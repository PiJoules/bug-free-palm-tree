import java.util.ArrayList;
import java.util.Scanner;

/**
 * Singlueton with helper functions.
 */
public class Utils {
    public static final int MAX_ENTER_COUNT = 2;

    public static void showPrompt(){
        VoiceStream.println("Enter the prompt for your question:");
        VoiceStream.println("You can enter a single newline by pressing enter once.");
        VoiceStream.println("Press enter twice in a row to finish entering the question.");
    }

    /**
     * Keep reading lines until enter is pressed N times in a row.
     */
    public static ArrayList<String> readMultipleLines(Scanner scanner){
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
    public static String readMultiLineString(Scanner scanner, String delimiter){
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
    public static String readMultiLineString(Scanner scanner){
        return readMultiLineString(scanner, "\n");
    }
}
