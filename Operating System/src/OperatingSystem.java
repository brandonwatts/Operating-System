import java.util.function.Consumer;

public class OperatingSystem {
	private Clock clock;
	private Memory memory;
	private Prompt prompt;
	
	private Consumer<String> consumer = (String input) -> {
		process(input);
		System.out.println(input);
	};
	
	public OperatingSystem() {
		clock = new Clock();
		memory = new Memory(256 * 1024);
		prompt = new Prompt(consumer);
	}
	
	public void execute() {
		clock.execute();
	}
	
	public void process(String input) {
		switch(input.toUpperCase()) {
			case "PROC":
				//TODO
				break;
			
			case "MEM":
				//TODO
				break;
				
			case "LOAD":
				//TODO
				break;
			
			case "EXE":
				//TODO
				break;
			
			case "RESET":
				prompt.setText(null);
				break;
			
			case "EXIT":
				System.exit(0);
				break;
			
			case "HELP":
				prompt.append("The List of availible commands are: proc, mem, load, exe, reset, and exit" + "\n");
				break;
			
			default:
				prompt.append("Error: Not a valid input" + "\n");
    	}
	}
}
