package os.instruction;

import os.OperatingSystem;

public class OpenBracket implements Instruction {
	@Override
	public void execute() {
		Object data = OperatingSystem.memory.read(OperatingSystem.cpu.registers[OperatingSystem.PROC_DATA_POINTER]);
		if (data == null) {
			data = new Integer(0);
		}
		
		if (data.equals(0)) {
			int balance = 1;
			OperatingSystem.cpu.registers[OperatingSystem.INSTRUCTION_REGISTER]++;
			while (balance != 0) {
				Object value = OperatingSystem.memory.read(OperatingSystem.cpu.registers[OperatingSystem.INSTRUCTION_REGISTER]++);
				if (value instanceof OpenBracket) {
					balance++;
				} else if (value instanceof CloseBracket) {
					balance--;
				} else if (value instanceof Exit) {
					balance = 0;
				}
			}
			
			OperatingSystem.cpu.registers[OperatingSystem.INSTRUCTION_REGISTER]--;
		} else {
			OperatingSystem.cpu.registers[OperatingSystem.INSTRUCTION_REGISTER]++;
		}
	}
	
	@Override
	public String toString() {
		return "[";
	}
}
