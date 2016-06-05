import java.util.Scanner;
import java.io.IOException;

public class SaveDriver<T extends Questionnaire> extends Driver {
    /**
     * No need to print anything other than the questionnaire
     */
    public void showPrompt(){
        VoiceStream.print("");
    }

    /**
     * Args:
     *      scan (Scanner)
     *      questionnaire (Questionnaire)
     */
    @SuppressWarnings("unchecked")
    public Driver handleInput(Scanner scan, Object... args){
        if (args[0] != null){
            Questionnaire questionnaire = (T)args[0];
            VoiceStream.print("Enter the filename you would like to save this questionnaire to: ");
            String filename = scan.next();
            try {
                questionnaire.save(filename);
            }
            catch (IOException e){
                System.err.println("Could not save file: " + e.getMessage());
                e.printStackTrace();
            }
        }
        else {
            System.err.println("Could not save questionnaire since none was loaded or created prior.");
        }
        return new Menu1Driver();
    }
}
