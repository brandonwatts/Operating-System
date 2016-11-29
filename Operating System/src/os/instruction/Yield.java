package os.instruction;

import os.OperatingSystem;
import os.ui.TaskManager;

public class Yield implements Instruction {
	@Override
	public void execute() {
		OperatingSystem.cpu.registers[OperatingSystem.INSTRUCTION_REGISTER]++;
		TaskManager.updateTaskManager();
	}
	
	@Override
	public String toString() {
		return "YIELD";
	}
}
