package os;

import java.util.ArrayList;

public class Memory {
	Object[] memory = new Object[OperatingSystem.MEMORY_SIZE];
	Page[] table = new Page[OperatingSystem.MEMORY_SIZE / OperatingSystem.PAGE_SIZE];
	
	public Memory() {
		for (int i = 0; i < table.length; i++) {
			table[i] = new Page();
			table[i].owner = null;
			table[i].free = true;
		}
	}
	
	public boolean requestMemory(Process process, int size) {
		int pages = (size + OperatingSystem.PAGE_SIZE - 1) / OperatingSystem.PAGE_SIZE;
		
		for (int i = pages; i < table.length; i++) {
			boolean free = true;
			
			for (int j = i - pages; j < i; j++) {
				if (!table[j].free) {
					free = false;
					break;
				}
			}
			
			if (free) {				
				for (int j = i - pages; j < i; j++) {
					table[j].free = false;
					table[j].owner = process;
				}
				
				process.registers[OperatingSystem.PROC_BASE_REGISTER] = OperatingSystem.PAGE_SIZE * (i - pages);
				process.registers[OperatingSystem.PROC_LIMIT_REGISTER] = OperatingSystem.PAGE_SIZE * i;
				
				return true;
			}
		}
		
		return false;
	}
	
	public void freeMemory(Process process) {
		for (int i = 0; i < table.length; i++) {
			if (table[i].owner == process) {
				table[i].free = true;
			}
		}
	}
	
	public int getUsedMemory() {
		int used = 0;
		
		for (int i = 0; i < table.length; i++) {
			if (!table[i].free) {
				used += OperatingSystem.PAGE_SIZE;
			}
		}
		
		
		return used;
	}
	
	public int getFreeMemory() {
		int free = 0;
		
		for (int i = 0; i < table.length; i++) {
			if (table[i].free) {
				free += OperatingSystem.PAGE_SIZE;
			}
		}
		
		
		return free;
	}
}