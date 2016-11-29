package os.instruction;

import os.OperatingSystem;
import os.Process;
import os.ProcessState;

public class Exit implements Instruction {
	@Override
	public void execute() {
		int id = OperatingSystem.cpu.registers[OperatingSystem.PROCESS_ID_REGISTER];
		Process process = OperatingSystem.scheduler.getProcessByID(id);
		process.state = ProcessState.EXIT;
		OperatingSystem.memory.freeMemory(process);
		OperatingSystem.hardDrive.freeMemory(id);
		OperatingSystem.scheduler.remove(process);
		OperatingSystem.cpu.registers[OperatingSystem.PROCESS_ID_REGISTER] = -1;
		
		OperatingSystem.taskManager.refreshStatistics();
		OperatingSystem.taskManager.removeFromModel(process);
	}
	
	@Override
	public String toString() {
		return "EXIT";
	}
}
