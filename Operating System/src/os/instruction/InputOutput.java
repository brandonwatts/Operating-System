package os.instruction;

import os.Process;
import os.ProcessState;

import java.util.Random;

public class InputOutput implements Instruction {
	@Override
	public void execute(Process process) {
		process.state = ProcessState.WAIT;
	}
	
	@Override
	public String toString() {
		return "IO ";
	}
}
