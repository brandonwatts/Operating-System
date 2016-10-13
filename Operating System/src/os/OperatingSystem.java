package os;

import java.util.ArrayList;

public class OperatingSystem {
	public static final int AMOUNT_OF_MEMORY = 256 * 1024;
	public static final int NUMBER_OF_REGISTERS = 4;
	public static final int INSTRUCTION_REGISTER = 0;
	
	public static Clock clock;
	public static Memory memory;
	public static Prompt prompt;
	public static Scheduler scheduler;
	
	public static void initialize() {
		clock = new Clock();
		memory = new Memory();
		prompt = new Prompt();
		scheduler = new Scheduler();
	}
	
	public static void execute() {
		scheduler.execute();
		clock.execute();
	}
	
	public static void process(String input) {
		String[] tokens = input.trim().split("\\s+");
		
		if (tokens.length == 0) {
			return;
		}
		
		switch (tokens[0].toUpperCase()) {
			case "PROC":
				//TODO
				break;
			
			case "MEM":
				//TODO
				break;
				
			case "LOAD":
				load(tokens);
				break;
			
			case "EXE":
				exe(tokens);
				break;
			
			case "RESET":
				prompt.setText(null);
				break;
			
			case "EXIT":
				System.exit(0);
				break;
			
			case "HELP":
				prompt.append("The List of availible commands are: proc, mem, load, exe, reset, and exit" + "\n");
				break;
				
			case "TSKMGR":
				TaskManagerInitializer taskManager = new TaskManagerInitializer();
				break;
				
			default:
				prompt.append("Error: Not a valid input" + "\n");
    	}
	}
	
	private static void exe(String[] arguments) {
		try {
			// run for a single cycle
			if (arguments.length == 1) {
				execute();
			}
			
			// run for the specified number of cycles
			else if (arguments.length == 2) {
				String number = arguments[1];
				int numberOfCycles = 0;
				
				try {
					numberOfCycles = Integer.parseInt(number);
				} catch (Exception e) {
					throw new Exception("The number - " + number + " - is incorrect.");
				}
				
				for (int i = 0; i < numberOfCycles; i++) {
					execute();
				}
			}
			
			// bad use of exe
			else {
				throw new Exception("Incorrect use of exe.");
			}
		} catch (Exception e) {
			prompt.append(e.getMessage() + "\n");
		}
	}
	
	private static void load(String[] arguments) {
		try {
			JobFileData job = null;
			
			// load in a job file
			if (arguments.length == 2) {
				String filename = arguments[1];
				job = JobFileData.readJobFile(filename);
			}
			
			// load in a program file with a time
			else if (arguments.length == 3) {
				String filename = arguments[1];
				String time = arguments[2];
				
				job = new JobFileData();
				job.programs.add(ProgramFileData.readProgramFile(filename));
				
				try {
					job.startTimes.add(Integer.parseInt(time));
				} catch (NumberFormatException e) {
					throw new Exception("The number - " + time + " - is incorrect.");
				}
			}
			
			// bad use of load
			else {
				throw new Exception("Incorrect use of load.");
			}
			
			prompt.append("Files were successfully read in.\n");
			
			ArrayList<Process> processes = job.generateProcesses();
			for (Process process : processes) {
				scheduler.insertPCB(process);
			}
		} catch (Exception e) {
			prompt.append(e.getMessage() + "\n");
		}
		
	}
}
