package os.instruction;

import os.OperatingSystem;
import os.Process;

public class Exit implements Instruction {
	@Override
	public void execute() {
		int id = OperatingSystem.cpu.registers[OperatingSystem.PROCESS_ID_REGISTER];
		Process process = OperatingSystem.scheduler.getProcessByID(id);
		OperatingSystem.memory.freeMemory(process);
		OperatingSystem.scheduler.remove(process);
		OperatingSystem.cpu.registers[OperatingSystem.PROCESS_ID_REGISTER] = -1;
	}
}
