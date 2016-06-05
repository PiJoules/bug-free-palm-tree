import java.util.Scanner;

public abstract class MenuDriver extends Driver {
    protected abstract String[] options();
    protected abstract String header();

    public void showPrompt(){
        VoiceStream.println(header());
        for (String option : options()){
            VoiceStream.println(option);
        }
    }

    /**
     * Return number selected.
     * Return null if invalid number and prints an error message.
     */
    protected Integer selection(Scanner scanner){
        if (!scanner.hasNextInt()){
            printInvalidEntry(scanner.next());
            return null;
        }
        int selection = scanner.nextInt();
        if (selection < 1 || selection > options().length){
            printInvalidEntry(selection + "");
            return null;
        }
        return selection;
    }

    private void printInvalidEntry(String entry){
        VoiceStream.println("Invalid entry: " + entry);
        VoiceStream.printf("Valid entries are: 1 - %d\n", options().length);
    }
}
