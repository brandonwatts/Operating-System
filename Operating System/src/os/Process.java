package os;

/**
 * This class represents a process control block.
 */

public class Process {
    // Holds the current state of the Process
    public ProcessState state;
    // Array to hold the state of the registers
    public int[] registers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMemoryUseage() { return memoryUseage; }

    // Name of the process
    public String name;

    private int memoryUseage;

    public Process(ProcessData data, int identifier) {
        this.registers = new int[OperatingSystem.NUMBER_OF_REGISTERS];
        this.name = data.name;
        this.memoryUseage = data.memory;
		
		OperatingSystem.memory.requestMemory(this, data.memory);
		
        this.state = ProcessState.READY;
        int instruction = this.registers[OperatingSystem.PROC_BASE_REGISTER];
        this.registers[OperatingSystem.INSTRUCTION_REGISTER] = instruction;
        this.registers[OperatingSystem.PROCESS_ID_REGISTER] = identifier;
    }
	
	public int getID() {
		return this.registers[OperatingSystem.PROCESS_ID_REGISTER];
	}

    @Override
    public String toString() {
        String string = "";

        string += "Process Information\n";
        string += "name: " + name + "\n";
        string += "state: " + state + "\n";

        return string;
    }
}