package os.instruction;

import os.OperatingSystem;

public class DecrementValue implements Instruction {
	@Override
	public void execute() {
		OperatingSystem.cpu.registers[OperatingSystem.INSTRUCTION_REGISTER]++;
		
		int pointer = OperatingSystem.cpu.registers[OperatingSystem.PROC_DATA_POINTER];
		Object value = OperatingSystem.memory.read(pointer);
		
		if (value == null) {
			value = new Integer(0);
		}
		
		OperatingSystem.memory.write(pointer, ((Integer) value) - 1);
	}
	
	@Override
	public String toString() {
		return "-";
	}
}
