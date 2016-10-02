public class Calculate implements Instruction {
	
	private int time;
	
	public Calculate(int time) {
		this.time = time;
	}
	
	@Override
	public void execute(Process process) {
		// TODO
	}
	
	@Override
	public String toString() {
		return "CALCULATE " + time;
	}
}
