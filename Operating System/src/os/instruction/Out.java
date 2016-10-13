package os.instruction;

import os.OperatingSystem;
import os.Process;
import os.ProcessState;

public class Out implements Instruction {
	@Override
	public void execute(Process process) {
		OperatingSystem.prompt.append(process + "\n");
		process.state = ProcessState.READY;
	}
	
	@Override
	public String toString() {
		return "OUT";
	}
}
