package os.instruction;

import os.OperatingSystem;
import os.Tasks;

public class Yield implements Instruction {
	@Override
	public void execute() {
		OperatingSystem.cpu.registers[OperatingSystem.INSTRUCTION_REGISTER]++;
		Tasks.updateTaskManager();
	}
	
	@Override
	public String toString() {
		return "YIELD";
	}
}
