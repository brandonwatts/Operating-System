package os;

public class Clock implements Interrupter {
	public int clockCycle;
	
	public Clock() {
		this.clockCycle = 0;
	}
	
	public void execute() {
		clockCycle++;
		
		if (clockCycle % OperatingSystem.QUANTUM == 0) {
			OperatingSystem.cpu.signalInterrupt(this);
		}
	}
	
	@Override
	public void interrupt() {
		OperatingSystem.dispatcher.load(OperatingSystem.scheduler.nextReady(), ProcessState.READY);
	}
}
