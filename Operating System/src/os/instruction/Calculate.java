package os.instruction;

import os.OperatingSystem;
import os.ui.TaskManager;

public class Calculate implements Instruction {
	
	private int time;
	
	public Calculate(int time) {
		this.time = time;
	}
	
	@Override
	public void execute() {
		System.out.println(this.toString());
		
		if (time-- <= 0) {
			OperatingSystem.cpu.registers[OperatingSystem.INSTRUCTION_REGISTER]++;
			TaskManager.updateTaskManager();
		}
	}
	
	@Override
	public String toString() {
		return "CALCULATE " + time;
	}
}
