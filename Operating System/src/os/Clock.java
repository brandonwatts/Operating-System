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
		System.out.println("Clock Interrupt");
		
		OperatingSystem.dispatcher.load(OperatingSystem.scheduler.nextReady());
	}
}
