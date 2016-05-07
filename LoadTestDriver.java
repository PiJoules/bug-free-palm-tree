import java.util.Scanner;

public class LoadTestDriver extends Driver {
    private Test test = null;

    public void showPrompt(){
        System.out.println("Enter filename of test to load:");
    }

    public Driver handleInput(Scanner scan, Object... args){
        String filename = scan.next();
        try {
            test = new Test(filename);
            System.out.println("Successfully loaded " + filename);
        }
        catch (Exception e){
            System.err.printf("Could not load from file '%s': %s\n", filename, e.getMessage());
        }
        return new Menu1Driver();
    }

    public Test getTest(){
        return test;
    }
}
