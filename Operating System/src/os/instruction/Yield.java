package os.instruction;

import os.OperatingSystem;

public class Yield implements Instruction {
	@Override
	public void execute() {
		OperatingSystem.cpu.registers[OperatingSystem.INSTRUCTION_REGISTER]++;
	}
	
	@Override
	public String toString() {
		return "YIELD";
	}
}
