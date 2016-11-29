package os.instruction;

import os.OperatingSystem;

public class DecrementPointer implements Instruction {
	@Override
	public void execute() {
		OperatingSystem.cpu.registers[OperatingSystem.INSTRUCTION_REGISTER]++;
		
		int pointer = --OperatingSystem.cpu.registers[OperatingSystem.PROC_DATA_POINTER];
		int base = OperatingSystem.cpu.registers[OperatingSystem.PROC_BASE_POINTER];
		int limit = OperatingSystem.cpu.registers[OperatingSystem.PROC_LIMIT_REGISTER];
		
		if (pointer < base) {
			OperatingSystem.cpu.registers[OperatingSystem.PROC_DATA_POINTER] = limit - 1;
		}
	}
	
	@Override
	public String toString() {
		return "<";
	}
}
