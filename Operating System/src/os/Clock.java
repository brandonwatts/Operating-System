package os;

public class Clock {
	public int clockCycle;
	
	public Clock() {
		this.clockCycle = 0;
	}
	
	public int getClock() {
		return clockCycle;
	}
	
	public void execute() {
		clockCycle++;
	}
}
