package os;

public class Clock implements Interrupter {
	public int clockCycle;
	
	public Clock() {
		this.clockCycle = 0;
	}
	
	public void execute() {
		clockCycle++;
		System.out.println("clock " + clockCycle);
		
		if (clockCycle % OperatingSystem.QUANTUM == 0) {
			System.out.println("Signalling interrupt");
			OperatingSystem.cpu.signalInterrupt(this);
		}
	}
	
	@Override
	public void interrupt() {
		System.out.println("interrupting");
		Process process = OperatingSystem.scheduler.next();
		if (process != null) {
			OperatingSystem.dispatcher.load(process);
		}
	}
}
