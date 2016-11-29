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
		
		OperatingSystem.memory.requestMemory(this, data.memory + 2);
		
        this.state = ProcessState.READY;
        int base = this.registers[OperatingSystem.PROC_BASE_REGISTER];
        this.registers[OperatingSystem.INSTRUCTION_REGISTER] = base + 1;
        this.registers[OperatingSystem.PROCESS_ID_REGISTER] = identifier;
		this.registers[OperatingSystem.PROC_BASE_POINTER] = base + data.instructions.size();
		this.registers[OperatingSystem.PROC_DATA_POINTER] = base + data.instructions.size();
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
		
		for (int i = 0; i < OperatingSystem.NUMBER_OF_REGISTERS; i++) {
			string += "registers[" + i + "] = " + registers[i] + "\n";
		}
		
		int address = registers[OperatingSystem.PROC_BASE_REGISTER];
		boolean success = false;
		
		while (OperatingSystem.memory.memory[address] != null) {
			int page = OperatingSystem.memory.getPage(address);
			
			boolean free = OperatingSystem.memory.table[page].free;
			boolean owned = OperatingSystem.memory.table[page].owner == this;
			
			if (owned && !free) {
				success = true;
				string += address + " " + OperatingSystem.memory.memory[address].toString() + "\n";
			}
			
			address++;
		}
		
		if (!success) {
			string += "PAGE NOT IN MEMORY\n";
		}

        return string;
    }
}