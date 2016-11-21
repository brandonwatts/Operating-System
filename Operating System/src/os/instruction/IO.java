package os.instruction;

import os.OperatingSystem;
import os.Process;
import os.ProcessState;

public class IO implements Instruction {
	@Override
	public void execute() {
		System.out.println("IO INSTRUCTION EXECUTING");
		
		int id = OperatingSystem.cpu.registers[OperatingSystem.PROCESS_ID_REGISTER];
		Process process = OperatingSystem.scheduler.getProcessByID(id);
		
		process.state = ProcessState.WAIT;
		OperatingSystem.cpu.registers[OperatingSystem.INSTRUCTION_REGISTER]++;
		
		for (int i = 0; i < OperatingSystem.NUMBER_OF_REGISTERS; i++) {
			process.registers[i] = OperatingSystem.cpu.registers[i];
		}
		
		OperatingSystem.cpu.registers[OperatingSystem.PROCESS_ID_REGISTER] = -1;
		
		OperatingSystem.scheduler.readyQueue.remove(process);
		OperatingSystem.scheduler.ioQueue.add(process);
	}
	
	@Override
	public String toString() {
		return "IO ";
	}
}
