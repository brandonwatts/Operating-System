import java.util.function.Consumer;

public class Main{
	
	static Prompt prompt;
	static Consumer<String> consumer;
	
    public static void main(String[] args) {
    	
    	InputProcessor input = InputProcessor.getInstance();
    	
         consumer = (String string) -> {
            System.out.println(string);
            input.process(string);
        };
       
        prompt = new Prompt(consumer);
        
     
    }

}
