import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Consumer<String> consumer = (String string) -> {
            System.out.println(string);
            if (string.equals("exit"))
                System.exit(0);
        };
        
        Prompt prompt = new Prompt(consumer);
    }
}
