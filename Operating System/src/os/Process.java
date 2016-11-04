package os;

import java.util.ArrayList;

/**
 * This class represents a process control block.
 */

public class Process {
	// Holds the current state of the Process
	public ProcessState state;
	
	// Array to hold the state of the registers
	public int[] registers;
	
	// Name of the process
	public String name;
	
	private static int identifier = 1;
	
	public Process(ProcessData data, int identifier) {
		this.registers = new int[OperatingSystem.NUMBER_OF_REGISTERS];
		this.name = data.name;
		
		if (!OperatingSystem.memory.requestMemory(this, data.memory)) {
			this.state = null;
			return;
		}
		
		this.state = ProcessState.READY;
		
		for (int i = 0; i < data.instructions.size(); i++) {
			int index = i + this.registers[OperatingSystem.PROC_BASE_REGISTER];
			OperatingSystem.memory.memory[index] = data.instructions.get(i);
		}
		
		int instruction = this.registers[OperatingSystem.PROC_BASE_REGISTER];
		this.registers[OperatingSystem.INSTRUCTION_REGISTER] = instruction;
		this.registers[OperatingSystem.PROCESS_ID_REGISTER] = identifier;
	}
	
	@Override
	public String toString() {
		String string = "";
		
		string += "Process Information\n";
		string += "name: " + name + "\n";
		string += "state: " + state + "\n";
		string += "registers[0] = " + registers[0] + "\n";
		string += "registers[1] = " + registers[1] + "\n";
		string += "registers[2] = " + registers[2] + "\n";
		string += "registers[3] = " + registers[3] + "\n";
		
		return string;
	}
}