package os;

import java.util.concurrent.ThreadLocalRandom;

import os.OperatingSystem;
import os.Process;
import os.ProcessState;

public class IODevice implements Interrupter {
	private int counter = 25;
	
	public void execute() {
		if (counter-- <= 0) {
			OperatingSystem.cpu.signalInterrupt(this);
			counter = ThreadLocalRandom.current().nextInt(20, 25);
		}
	}
	
	@Override
	public void interrupt() {
		Process process = OperatingSystem.scheduler.nextIO();
		
		if (process != null) {
			process.state = ProcessState.READY;
			OperatingSystem.scheduler.readyQueue.add(process);
		}
	}
}
