import java.util.PriorityQueue;
import java.util.Stack;

public class Process {
	
	private ProcessStates state;
	private StringBuilder programCode;
	private int[] registers;
	private Stack<Object> stack;
	private PriorityQueue<Object> heap;
	
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

	public static class ProcessBuilder {
		
		private ProcessStates state;
		private StringBuilder programCode;
		private int[] registers;
		private Stack<Object> stack;
		private PriorityQueue<Object> heap;

		public ProcessBuilder(ProcessStates state, StringBuilder programCode, int[] registers, Stack<Object> stack, PriorityQueue<Object> heap) {
			this.state = state;
			this.programCode = programCode;
			this.registers = registers;
			this.stack = stack;
			this.heap = heap;
		}

		public ProcessBuilder state(ProcessStates state) {
			this.state = state;
			return this;
		}

		public ProcessBuilder programCode(StringBuilder programCode) {
			this.programCode = programCode;
			return this;
		}

		public ProcessBuilder registers(int[] registers) {
			this.registers = registers;
			return this;
		}

		public Process build() {
			return new Process(this);
		}

	}
}