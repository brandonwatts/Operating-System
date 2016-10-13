package os;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Scheduler {
	private ArrayList<Process> newQueue;
	private ArrayList<Process> readyQueue;
	private ArrayList<Process> runQueue;
	private ArrayList<Process> waitQueue;
	private ArrayList<Process> exitQueue;
	
	public Scheduler() {
		this.newQueue = new ArrayList<Process>();
		this.readyQueue = new ArrayList<Process>();
		this.runQueue = new ArrayList<Process>();
		this.waitQueue = new ArrayList<Process>();
		this.exitQueue = new ArrayList<Process>();
	}
	
	private ArrayList<Process> getQueue(ProcessState state) {
		switch (state) {
			case NEW:
				return newQueue;
			
			case READY:
				return readyQueue;
			
			case RUN:
				return runQueue;
			
			case WAIT:
				return waitQueue;
			
			case EXIT:
				return exitQueue;
		}
		
		return null;
	}
	
	public void execute() {
		updateNewQueue();
		updateReadyQueue();
		updateRunQueue();
		updateWaitQueue();
		updateExitQueue();
	}
	
	private void updateNewQueue() {
		while (newQueue.size() > 0) {
			Process process = newQueue.get(0);
			if (OperatingSystem.memory.requestMemory(process.memory)) {
				process.state = ProcessState.READY;
				newQueue.remove(0);
				readyQueue.add(process);
			} else {
				break;
			}
		}
		
	}
	
	// pretty pointless
	private void updateReadyQueue() {
		while (readyQueue.size() > 0) {
			Process process = readyQueue.remove(0);
			runQueue.add(process);
		}
	}
	
	private void updateRunQueue() {
		while (runQueue.size() > 0) {
			Process process = runQueue.remove(0);
			int instruction = process.registers[OperatingSystem.INSTRUCTION_REGISTER]++;
			process.instructions.get(instruction).execute(process);
			getQueue(process.state).add(process);
		}
	}
	
	private void updateWaitQueue() {
		int i = 0;
		while (i < waitQueue.size()) {
			Process process = waitQueue.get(i);
			if (ThreadLocalRandom.current().nextInt(0, 4) == 0) {
				waitQueue.remove(i);
				process.state = ProcessState.READY;
				readyQueue.add(process);
			} else {
				i++;
			}
		}
	}
	
	private void updateExitQueue() {
		while (runQueue.size() > 0) {
			Process process = exitQueue.remove(0);
			OperatingSystem.memory.freeMemory(process.memory);
		}
	}
	
	public void insertPCB(Process block) {
		newQueue.add(block);
	}
	
	public void removePCB(Process block) {
		getQueue(block.state).remove(block);
	}
	
	public ProcessState getState(Process block) {
		return block.state;
	}
	
	public void setState(Process block, ProcessState state) {
		block.state = state;
	}
	
	public int getNumberOfProcesses() {
		return newQueue.size() + readyQueue.size() + runQueue.size() + waitQueue.size() + exitQueue.size();
	}
}
