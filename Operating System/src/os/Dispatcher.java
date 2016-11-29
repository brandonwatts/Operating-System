package os;

public class Dispatcher {
	public void load(Process process, ProcessState state) {
		int id = OperatingSystem.cpu.registers[OperatingSystem.PROCESS_ID_REGISTER];
		Process previous = OperatingSystem.scheduler.getProcessByID(id);
		
		if (previous != null) {
			for (int i = 0; i < OperatingSystem.NUMBER_OF_REGISTERS; i++) {
				previous.registers[i] = OperatingSystem.cpu.registers[i];
			}
			previous.state = state;
		}
		
		if (process != null) {
			for (int i = 0; i < OperatingSystem.NUMBER_OF_REGISTERS; i++) {
				OperatingSystem.cpu.registers[i] = process.registers[i];
			}
			process.state = ProcessState.RUN;
		} else {
			OperatingSystem.cpu.registers[OperatingSystem.PROCESS_ID_REGISTER] = -1;
		}
	}
}
