package os;

import java.util.concurrent.ThreadLocalRandom;

import os.OperatingSystem;
import os.Process;
import os.ProcessState;

public class IODevice implements Interrupter {
	public void execute() {
		if (ThreadLocalRandom.current().nextInt(0, 4) == 0) {
			OperatingSystem.cpu.signalInterrupt(this);
		}
	}
	
	@Override
	public void interrupt() {
		System.out.println("IODevice Interrupt");
		
		Process process = OperatingSystem.scheduler.nextIO();
		
		if (process != null) {
			process.state = ProcessState.READY;
			OperatingSystem.scheduler.readyQueue.add(process);
		}
	}
}
