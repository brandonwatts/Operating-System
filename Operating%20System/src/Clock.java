public class Clock {
	private int clockCycle = 0;
	
	public int getClock() {
		return clockCycle;
	}
	
	public void execute() {
		clockCycle++;
	}
}
