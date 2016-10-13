package os.instruction;

import os.OperatingSystem;
import os.Process;
import os.ProcessState;

import java.util.Random;

public class Yield implements Instruction {
	@Override
	public void execute(Process process) {
		process.state = ProcessState.WAIT;
	}
	
	@Override
	public String toString() {
		return "YIELD";
	}
}
