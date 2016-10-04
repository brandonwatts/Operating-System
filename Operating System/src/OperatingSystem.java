import java.util.function.Consumer;

public class OperatingSystem {
	private Clock clock;
	private Memory memory;
	private Prompt prompt;
	private ExecutionQueue executionQueue;
	
	private Consumer<String> consumer = (String input) -> {
		process(input);
		System.out.println(input);
	};
	
	public OperatingSystem() {
		clock = new Clock();
		memory = new Memory(256 * 1024);
		prompt = new Prompt(consumer);
		executionQueue = new ExecutionQueue();
	}
	
	public ExecutionQueue getExecutionQueue()
	{
		return this.executionQueue;
	}
	
	public Memory getMemory()
	{
		return this.memory;
	}
	
	public void execute() {
		clock.execute();
	}
	
	public void process(String input) {
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
				Job job = null;
				String message = "";
				
				try {
					// load in a job file
					if (tokens.length == 2) {
						job = loadJobFile(tokens[1]);
					}
					// load in a program file with a time
					else if (tokens.length == 3) {
						job = loadProgramFile(tokens[1], tokens[2]);
					}
					// bad use of load
					else {
						message = "The input - " + input + " - is bad.";
					}
				} catch (Exception e) {
					job = null;
					message  = e.getMessage();
				}
				
				// something went wrong
				if (job == null) {
					prompt.append(message + "\n");
				}
				// load the job file into the os
				else {
					prompt.append("Files were successfully read in.\n");
					prompt.append("TODO - Load the Job object into the OS\n");
					// TODO - Load the Job object into the OS
				}	
				break;
			
			case "EXE":
				//TODO
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
				TaskManagerInitializer taskManager = new TaskManagerInitializer(this);
				break;
				
			default:
				prompt.append("Error: Not a valid input" + "\n");
    	}
	}
	
	private Job loadJobFile(String filename) throws Exception {
		return Job.readJob(filename);
	}
	
	private Job loadProgramFile(String filename, String time) throws Exception {
		Job job = new Job();
		
		job.programs.add(Program.readProgram(filename));
		
		try {
			job.startTimes.add(Integer.parseInt(time));
		} catch (NumberFormatException e) {
			throw new Exception("The number - " + time + " - is bad.");
		}
		
		return job;
	}
}
