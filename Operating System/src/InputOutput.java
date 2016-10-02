import java.util.Random;

public class InputOutput implements Instruction {
	
	private static Random random = new Random();
	
	private int time;
	
	public InputOutput() {
		this.time = random.nextInt(256);
	}
	
	@Override
	public void execute(Process process) {
		// TODO
	}
	
	@Override
	public String toString() {
		return "IO " + time;
	}
}
