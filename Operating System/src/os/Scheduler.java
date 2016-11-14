package os;

import os.ui.ProcessTableModel;
import os.ui.TaskManager;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Scheduler {
	private ArrayList<ProcessData> newQueue;
	private ArrayList<Process> readyQueue;
	
	private int identifier = 1;
	
	public Scheduler() {
		this.newQueue = new ArrayList<>();
		this.readyQueue = new ArrayList<>();
	}
	
	public void execute() {
		updateNewQueue();
	}
	
	private void updateNewQueue() {
		ArrayList<ProcessData> addedProcesses = new ArrayList<>();
		
		for (ProcessData data : newQueue) {
			if (data.startTime <= OperatingSystem.clock.clockCycle) {
				Process process = new Process(data, identifier);
				
				if (process.state == null) {
					break;
				}

				OperatingSystem.taskManager.addToModel(process);
				readyQueue.add(process);
				addedProcesses.add(data);

				identifier++;
				
				while (getProcessByID(identifier) != null) {
					identifier++;
					
					if (identifier < 0) {
						identifier = 1;
					}
				}
			}
		}
		
		newQueue.removeAll(addedProcesses);
	}
	
	public Process next() {
		System.out.println("CAlled - " + readyQueue.size());
		
		if (readyQueue.isEmpty()) {
			return null;
		}
		
		Process process = this.readyQueue.remove(0);
		this.readyQueue.add(process);
		return process;
	}
	
	public void insertPCB(ProcessData data) {
		int i = 0;
		
		while (i < newQueue.size()) {
			if (data.startTime < newQueue.get(i).startTime) {
				break;
			}
			i++;
		}
		
		newQueue.add(i, data);
	}
	
	public void remove(Process process) {
		readyQueue.remove(process);
	}
	
	public int getNumberOfProcesses() {
		return newQueue.size() + readyQueue.size();
	}

	public Process getProcessByID(int id) {
		for (Process process : readyQueue) {
			if (process.registers[OperatingSystem.PROCESS_ID_REGISTER] == id) {
				return process;
			}
		}
		
		return null;
	}





}
