package os.instruction;

import os.OperatingSystem;
import os.Process;
import os.ProcessState;
import os.ui.TaskManager;

public class Yield implements Instruction {
	@Override
	public void execute() {
		OperatingSystem.cpu.registers[OperatingSystem.INSTRUCTION_REGISTER]++;
		OperatingSystem.dispatcher.load(OperatingSystem.scheduler.nextReady(), ProcessState.READY);
		TaskManager.updateTaskManager();
	}
	
	@Override
	public String toString() {
		return "YIELD";
	}
}
