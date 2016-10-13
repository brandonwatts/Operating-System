package os.instruction;

import os.Process;
import os.ProcessState;

public class Exit implements Instruction {
	@Override
	public void execute(Process process) {
		process.state = ProcessState.EXIT;
	}
}
