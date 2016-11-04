package os.instruction;

import os.OperatingSystem;

public class Calculate implements Instruction {
	
	private int time;
	
	public Calculate(int time) {
		this.time = time;
	}
	
	@Override
	public void execute() {
		System.out.println("left = " + time + " will become " + (time - 1));
		if (time-- <= 0) {
			OperatingSystem.cpu.registers[OperatingSystem.INSTRUCTION_REGISTER]++;
		}
	}
	
	@Override
	public String toString() {
		return "CALCULATE " + time;
	}
}
