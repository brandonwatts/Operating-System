package os.instruction;

import os.OperatingSystem;

public class Print implements Instruction {
	@Override
	public void execute() {
		OperatingSystem.cpu.registers[OperatingSystem.INSTRUCTION_REGISTER]++;
		Object data = OperatingSystem.memory.read(OperatingSystem.cpu.registers[OperatingSystem.PROC_DATA_POINTER]);
		if (data == null) {
			data = new Integer(0);
		}
		System.out.print((char) (int) data);
	}
	
	@Override
	public String toString() {
		return ".";
	}
}
