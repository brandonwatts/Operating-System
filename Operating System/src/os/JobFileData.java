package os;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class JobFileData {
	public ArrayList<ProgramFileData> programs;
	public ArrayList<Integer> startTimes;
	
	public JobFileData() {
		this.programs = new ArrayList<ProgramFileData>();
		this.startTimes = new ArrayList<Integer>();
	}
	
	public JobFileData(ArrayList<ProgramFileData> programs, ArrayList<Integer> startTimes) {
		this.programs = programs;
		this.startTimes = startTimes;
	}
	
	public static JobFileData readJobFile(String filename) throws Exception {
		Scanner scanner;
		
		// try to open the file
		try {
			scanner = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			throw new Exception("The file - " + filename + " - was not found.");
		}
		
		ArrayList<ProgramFileData> programs = new ArrayList<ProgramFileData>();
		ArrayList<Integer> startTimes = new ArrayList<Integer>();
		
		boolean end = false;
		
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] tokens = line.trim().split("\\s+");
			
			if (tokens.length == 0) {
				continue;
			} else if (tokens.length == 1) {
				if (!tokens[0].equals("EXE")) {
					throw new Exception("In file - " + filename + " - " + "the line - " + line + " - is bad.");
				} else {
					end = true;
					break;
				}
			} else if (tokens.length == 3) {
				if (!tokens[0].equals("LOAD")) {
					throw new Exception("In file - " + filename + " - " + "the line - " + line + " - is bad.");
				}
				
				try {
					startTimes.add(Integer.parseInt(tokens[2]));
				} catch (NumberFormatException e) {
					throw new Exception("In file - " + filename + " - " + "the number - " + tokens[2] + " - is bad.");
				}
				
				programs.add(ProgramFileData.readProgramFile(tokens[1]));
			} else {
				throw new Exception("In file - " + filename + " - the line - " + line + " - has to many arguments.");
			}
		}
		
		if (!end) {
			throw new Exception("In file - " + filename + " - no EXE line was found.");
		}
		
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			if (line.trim().length() > 0) {
				throw new Exception("In file - " + filename + " - the line - " + line + " - is bad.");
			}
		}
		
		return new JobFileData(programs, startTimes);
	}
	
	public ArrayList<ProcessData> generateProcesses() {
		ArrayList<ProcessData> processes = new ArrayList<ProcessData>();
		
		for (int i = 0; i < programs.size(); i++) {
			processes.add(new ProcessData(programs.get(i), startTimes.get(i)));
		}
		
		return processes;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < programs.size(); i++) {
			builder.append("LOAD " + programs.get(i).name + " " + startTimes.get(i) + "\n");
		}
		
		builder.append("EXE");
		return builder.toString();
	}
}