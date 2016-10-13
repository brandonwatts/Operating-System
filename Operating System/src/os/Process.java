package os;

import os.instruction.Instruction;

import java.util.ArrayList;

/**
 * This class represents a process control block.
 */

public class Process {
	/** Holds the current state of the Process **/
	public ProcessState state;
	
	/** Program for the program code **/
	public ArrayList<Instruction> instructions;
	
	/** Array to hold the state of the registers **/
	public int[] registers;
	
	/** Stack to hold variables **/
	// TODO
	// public Stack stack;
	
	/** Heap to hold variables **/
	// TODO
	// public Heap heap;
	
	/** Start time for the process **/
	public int startTime;
	
	/** Amount of memory held by the process **/
	public int memory;
	
	/** Name of the process **/
	public String name;
	
	public Process(ProgramFileData data, int startTime) {
		this.state = ProcessState.NEW;
		this.instructions = data.instructions;
		this.registers = new int[OperatingSystem.NUMBER_OF_REGISTERS];
		this.memory = data.memory;
		this.name = data.name;
		this.startTime = startTime;
	}
	
	@Override
	public String toString() {
		String string = "";
		
		string += "Process Information\n";
		string += "name: " + name + "\n";
		string += "TODO - Add in the rest of the information";
		
		return string;
	}
}