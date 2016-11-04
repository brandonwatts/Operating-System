package os;

import os.instruction.Instruction;

import java.util.ArrayList;

public class ProcessData {
	// Program for the program code
	public ArrayList<Instruction> instructions;
	
	// Name of the process
	public String name;
	
	// Start time for the process
	public int startTime;
	
	// Memory needed by the process
	public int memory;
	
	public ProcessData(ProgramFileData data, int startTime) {
		this.instructions = data.instructions;
		this.memory = data.memory + data.instructions.size();
		this.name = data.name;
		this.startTime = startTime;
	}
	
	@Override
	public String toString() {
		String string = "";
		
		string += "Process Information\n";
		string += "name: " + name + "\n";
		string += "startTime: " + startTime + "\n";
		string += "memory: " + memory + "\n";
		
		return string;
	}
}
