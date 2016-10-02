import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Job {
	
	public ArrayList<Program> programs;
	public ArrayList<Integer> startTimes;
	
	public Job() {
		this.programs = new ArrayList<Program>();
		this.startTimes = new ArrayList<Integer>();
	}
	
	public Job(ArrayList<Program> programs, ArrayList<Integer> startTimes) {
		this.programs = programs;
		this.startTimes = startTimes;
	}
	
	public static Job readJob(String filename) throws Exception {
		Scanner scanner;
		
		// try to open the file
		try {
			scanner = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			throw new Exception("The file - " + filename + " - was not found.");
		}
		
		ArrayList<Program> programs = new ArrayList<Program>();
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
				
				programs.add(Program.readProgram(tokens[1]));
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
		
		return new Job(programs, startTimes);
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