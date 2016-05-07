import java.util.Scanner;

public class DisplayDriver<T extends Questionnaire> extends Driver {
    /**
     * No need to print anything other than the questionnaire
     */
    public void showPrompt(){
        System.out.print("");
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
            System.out.println("");
            System.out.println(questionnaire.toString());
        }
        else {
            System.err.println("Could not display questionnaire since none was loaded or created prior.");
        }
        return new Menu1Driver();
    }
}
