public class ConsoleStream implements OutputStream {
    public void print(String s){
        System.out.print(s);
    }
    public void println(String s){
        System.out.println(s);
    }
    public void printf(String s, Object... objs){
        System.out.printf(s, objs);
    }
}
