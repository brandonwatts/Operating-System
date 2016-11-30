package os;

import org.jfree.ui.RefineryUtilities;
import os.ui.MemoryGraph;
import os.ui.ProcGraph;
import os.ui.Prompt;
import os.ui.TaskManager;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class OperatingSystem {
	// must be divisible by the page size
    public static final int MEMORY_SIZE = 256 * 1024;
    public static final int PAGE_SIZE = 4 * 1024;

    public static final int NUMBER_OF_REGISTERS = 6;
    public static final int INSTRUCTION_REGISTER = 0;
    public static final int PROC_BASE_REGISTER = 1;
    public static final int PROC_LIMIT_REGISTER = 2;
    public static final int PROCESS_ID_REGISTER = 3;
	public static final int PROC_BASE_POINTER = 4;
	public static final int PROC_DATA_POINTER = 5;
	
    public static final int QUANTUM = 10;
	
    public static Clock clock;
    public static CPU cpu;
    public static Dispatcher dispatcher;
    public static Memory memory;
    public static Scheduler scheduler;
	public static IODevice device;
	public static HardDrive hardDrive;
	public static MemoryGraph memoryGraph;
    public static ProcGraph procGraph;
    public static Prompt prompt;
    public static TaskManager taskManager;
    private static ArrayList<String> commandQueue;

    public static void initialize() {
        clock = new Clock();
        cpu = new CPU();
        dispatcher = new Dispatcher();
        memory = new Memory();
        scheduler = new Scheduler();
        device = new IODevice();
        hardDrive = new HardDrive();
        prompt = new Prompt();
        taskManager = new TaskManager();
        commandQueue = new ArrayList<>();
        memoryGraph = new MemoryGraph("Memory Useage");
        procGraph = new ProcGraph("Number of Processes");
    }

    public static void execute() {
        scheduler.execute();
        cpu.execute();
        clock.execute();
		device.execute();
    }

    public static ArrayList<String> getCommandQueue(){return commandQueue;}

    public static void process(String input) {
        String[] tokens = input.trim().split("\\s+");

        if (tokens.length == 0) {
            return;
        }
        StringBuilder commandHolder = new StringBuilder();
        for (String token : tokens) {
            commandHolder.append(token + " ");
        }
        commandQueue.add(commandHolder.toString());

        switch (tokens[0].toUpperCase()) {
            case "PROC":
                proc();
                break;

            case "MEM":
                mem();
                break;

            case "LOAD":
                load(tokens);
                break;

            case "EXE":
                exe(tokens);
                break;

            case "RESET":
                reset();
                break;

            case "EXIT":
                System.exit(0);
                break;

            case "HELP":
                prompt.append("The List of availible commands are: proc, mem, load, exe, reset, tskmgr, memgraph and exit" + "\n");
                break;

            case "TSKMGR":
                taskManager.setVisible(true);
                break;

            case "MEMGRAPH":
                memoryGraph.pack();
                RefineryUtilities.centerFrameOnScreen(memoryGraph);
                memoryGraph.setVisible(true);
                break;

            case "PROCGRAPH":
                procGraph.pack();
                RefineryUtilities.centerFrameOnScreen(procGraph);
                procGraph.setVisible(true);
                break;

            default:
                prompt.append("Error: Not a valid input" + "\n");
        }
    }
	
	private static void reset() {
		prompt.output(null);
		
		for (Process process : scheduler.readyQueue) {
			OperatingSystem.taskManager.refreshStatistics();
			OperatingSystem.taskManager.removeFromModel(process);
		}
		
		for (Process process : scheduler.ioQueue) {
			OperatingSystem.taskManager.refreshStatistics();
			OperatingSystem.taskManager.removeFromModel(process);
		}
		
		clock = new Clock();
		cpu = new CPU();
		dispatcher = new Dispatcher();
		memory = new Memory();
		scheduler = new Scheduler();
		device = new IODevice();
		hardDrive = new HardDrive();
	}
	
	private static void proc() {
		prompt.append(cpu.toString() + "\n");
		
		for (Process process : scheduler.readyQueue) {
			prompt.append(process.toString() + "\n");
		}
		
		for (Process process : scheduler.ioQueue) {
			prompt.append(process.toString() + "\n");
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
            e.printStackTrace();
            prompt.append(e.getMessage() + "\n");
        }
    }

    private static void mem() {
        String usedMemory = Integer.toString(memory.getUsedMemory());
        String freeMemory = Integer.toString(memory.getFreeMemory());
        String numProcesses = Integer.toString(OperatingSystem.scheduler.getNumberOfProcesses());
        String loadedProcesses = Integer.toString(OperatingSystem.scheduler.newQueue.size());
        String activeProcesses = Integer.toString(OperatingSystem.scheduler.readyQueue.size());
        StringBuilder memoryStats = new StringBuilder();

        memoryStats.append("Memory Information\n");
        memoryStats.append("Used Memory: " + usedMemory + "\n" );
        memoryStats.append("Free Memory: " + freeMemory + "\n"  );
        memoryStats.append("Number of Processes: " + numProcesses + "\n"  );
        memoryStats.append("Number of Loaded Processes: " + loadedProcesses + "\n" );
        memoryStats.append("Number of Running Processes: " + activeProcesses + "\n" );

        prompt.append(memoryStats.toString());
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

            ArrayList<ProcessData> processes = job.generateProcesses();
            for (ProcessData process : processes) {
                scheduler.insertPCB(process);
            }
        } catch (Exception e) {
            prompt.append(e.getMessage() + "\n");
        }

    }
}
