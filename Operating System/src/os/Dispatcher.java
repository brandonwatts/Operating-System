package os;

public class Dispatcher {
	public void load(Process process) {
		int id = OperatingSystem.cpu.registers[OperatingSystem.PROCESS_ID_REGISTER];
		Process previous = OperatingSystem.scheduler.getProcessByID(id);
		
		if (previous != null) {
			for (int i = 0; i < OperatingSystem.NUMBER_OF_REGISTERS; i++) {
				previous.registers[i] = OperatingSystem.cpu.registers[i];
			}
		}
		
		for (int i = 0; i < OperatingSystem.NUMBER_OF_REGISTERS; i++) {
			OperatingSystem.cpu.registers[i] = process.registers[i];
		}
	}
}
