package os;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import os.instruction.*;

public class ProgramFileData {
	public String name;
	public int memory;
	public ArrayList<Instruction> instructions;
	
	public ProgramFileData() {
		this.name = "";
		this.memory = 0;
		this.instructions = new ArrayList<Instruction>();
	}
	
	public ProgramFileData(String name, int memory, ArrayList<Instruction> instructions) {
		this.name = name;
		this.memory = memory;
		this.instructions = instructions;
	}
	
	public static ProgramFileData readProgramFile(String filename) throws Exception {
		Scanner scanner;
		
		// try to open the file
		try {
			scanner = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			throw new Exception("The file - " + filename + " - was not found.");
		}
		
		int memory = 0;
		ArrayList<Instruction> instructions = new ArrayList<Instruction>();
		
		// read the amount of memory for the first line
		String line = scanner.nextLine();
		try {
			memory = Integer.parseInt(line);
		} catch (NumberFormatException e) {
			throw new Exception("In file - " + filename + " - the number - " + line + " - is bad.");
		}
		
		// read through the instructions line by line
		while (scanner.hasNext()) {
			line = scanner.nextLine();
			String[] tokens = line.trim().split("\\s+");
			
			if (tokens.length == 0) {
				continue;
			} else if (tokens.length == 1) {
				switch (tokens[0]) {
					case "IO":
						instructions.add(new IO());
						break;
					
					case "YIELD":
						instructions.add(new Yield());
						break;
					
					case "OUT":
						instructions.add(new Out());
				}
			} else if (tokens.length == 2) {
				if (!tokens[0].equals("CALCULATE")) {
					throw new Exception("In file - " + filename + " - the line - " + line + " - is bad.");
				}
				
				try {
					int time = Integer.parseInt(tokens[1]);
					instructions.add(new Calculate(time));
				} catch (NumberFormatException e) {
					throw new Exception("In file - " + filename + " - the number - " + tokens[1] + " - is bad.");
				}
			} else {
				throw new Exception("In file - " + filename + " - the line - " + line + " - is bad.");
			}
		}
		
		instructions.add(new Exit());
		
		return new ProgramFileData(filename, memory, instructions);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(Integer.toString(this.memory));
		
		for (Instruction instruction : this.instructions) {
			builder.append("\n" + instruction);
		}
		
		return builder.toString();
	}
}
