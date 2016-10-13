package os.instruction;

import os.Process;
import os.ProcessState;

public class Calculate implements Instruction {
	
	private int time;
	
	public Calculate(int time) {
		this.time = time;
	}
	
	@Override
	public void execute(Process process) {
		process.state = ProcessState.READY;
	}
	
	@Override
	public String toString() {
		return "CALCULATE " + time;
	}
}
