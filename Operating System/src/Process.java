/**
 * This Class will represent a Process. To instantiate it use the Builder design pattern and call the .build() method
 */

import java.util.PriorityQueue;
import java.util.Stack;

public class Process {
	
	/** Stores the parent operating system of this process **/
	private OperatingSystem operatingSystem;
	
	/** Holds the current state of the Process **/
	private ProcessState state;
	
	/** Program for the program code (loaded from a text file) **/
	private Program programCode;
	
	/** Array to hold the state of the registers **/
	private int[] registers;
	
	/** Stack to hold variables **/
	private Stack<Object> stack;
	
	/** Heap to hold variables **/
	private PriorityQueue<Object> heap;
	
	/** Builder Class **/
	private Process(ProcessBuilder builder) {
		this.operatingSystem = builder.operatingSystem;
		this.state = builder.state;
		this.programCode = builder.programCode;
		this.registers = builder.registers;
		this.stack = builder.stack;
		this.heap = builder.heap;
		
	}
	
	public OperatingSystem getOperatingSystem() {
		return operatingSystem;
	}
	
	public ProcessState getState() {
		return state;
	}

	public Program getProgramCode() {
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
		private OperatingSystem operatingSystem;
		private ProcessState state;
		private Program programCode;
		private int[] registers;
		private Stack<Object> stack;
		private PriorityQueue<Object> heap;

		/** Takes only operating system as a constructing parameter **/
		/** Stack and heap start empty **/
		public ProcessBuilder(OperatingSystem operatingSystem) {
			this.operatingSystem = operatingSystem;
			this.stack = new Stack<Object>();
			this.heap = new PriorityQueue<Object>();
		}
		
		public ProcessBuilder setProcessState(ProcessState state) {
			this.state = state;
			return this;
		}

		public ProcessBuilder setProgramCode(Program programCode) {
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