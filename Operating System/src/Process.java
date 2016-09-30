/**
 * This Class will represent a Process. To instantiate it use the Builder design pattern and call the .build() method
 */

import java.util.PriorityQueue;
import java.util.Stack;

public class Process {
	
	/** Holds the current state of the Process **/
	private ProcessStates state;
	
	/** StringBuilder for the program code (should be a text file) **/
	private StringBuilder programCode;
	
	/** Array to hold the state of the registers **/
	private int[] registers;
	
	/** Stack to hold variables **/
	private Stack<Object> stack;
	
	/** Heap to hold variables **/
	private PriorityQueue<Object> heap;
	
	/** Builder Class **/
	private Process(ProcessBuilder builder) {
		
		this.state = builder.state;
		this.programCode = builder.programCode;
		this.registers = builder.registers;
		this.stack = builder.stack;
		this.heap = builder.heap;
		
	}

	public ProcessStates getState() {
		return state;
	}

	public StringBuilder getprogramCode() {
		return programCode;
	}

	public int[] getRegisters() {
		return registers;
	}

	public Stack<Object> getStack() {
		return stack;
	}

	public PriorityQueue<Object> getHeap() {
		return heap;
	}

	/** Builder Class **/
	public static class ProcessBuilder {
		
		private ProcessStates state;
		private StringBuilder programCode;
		private int[] registers;
		private Stack<Object> stack;
		private PriorityQueue<Object> heap;

		/** Takes only state as a constructing parameter (Stack and Heap start empty) **/
		public ProcessBuilder(ProcessStates state) {
			this.state = state;
			this.stack = new Stack<Object>();
			this.heap = new PriorityQueue<Object>();
		}

		public ProcessBuilder setprogramCode(StringBuilder programCode) {
			this.programCode = programCode;
			return this;
		}

		public ProcessBuilder setRegisters(int[] registers) {
			this.registers = registers;
			return this;
		}

		public Process build() {
			return new Process(this);
		}

	}
}