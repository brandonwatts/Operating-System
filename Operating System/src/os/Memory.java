package os;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

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
	
	public Object read(int address) {
		int id = OperatingSystem.cpu.registers[OperatingSystem.PROCESS_ID_REGISTER];
		int base = OperatingSystem.cpu.registers[OperatingSystem.PROC_BASE_REGISTER];
		int limit = OperatingSystem.cpu.registers[OperatingSystem.PROC_LIMIT_REGISTER];
		
		Process process = OperatingSystem.scheduler.getProcessByID(id);
		
		int page = getPage(address);
		int pageStart = page * OperatingSystem.PAGE_SIZE;
		int pageEnd = pageStart + OperatingSystem.PAGE_SIZE;
		
		if (id == 0) {
			return this.memory[address];
		} else {
			if (base <= address && address < limit) {
				if (table[page].owner.getID() == process.getID()) {
					return this.memory[address];
				} else {
					Process owner = this.table[page].owner;
					
					this.table[page].owner = process;
					this.table[page].free = false;
					
					if (owner != null) {
						int ownerID = owner.registers[OperatingSystem.PROCESS_ID_REGISTER];
						OperatingSystem.hardDrive.writeTo(new Request(pageStart, pageEnd, ownerID));
					}
					
					OperatingSystem.hardDrive.readFrom(new Request(pageStart, pageEnd, id));
					return this.memory[address];
				}
			} else {
				// send the CPU a trap
				return null;
			}
		}
	}
	
	public boolean write(int address, Object data) {
		int id = OperatingSystem.cpu.registers[OperatingSystem.PROCESS_ID_REGISTER];
		int base = OperatingSystem.cpu.registers[OperatingSystem.PROC_BASE_REGISTER];
		int limit = OperatingSystem.cpu.registers[OperatingSystem.PROC_LIMIT_REGISTER];
		
		Process process = OperatingSystem.scheduler.getProcessByID(id);
		
		int page = getPage(address);
		int pageStart = page * OperatingSystem.PAGE_SIZE;
		int pageEnd = pageStart + OperatingSystem.PAGE_SIZE;
		
		if (id == 0) {
			this.memory[address] = data;
			return true;
		} else {
			if (base <= address && address < limit) {
				if (table[page].owner == null || table[page].free) {
					table[page].owner = process;
					table[page].free = false;
					OperatingSystem.hardDrive.readFrom(new Request(pageStart, pageEnd, id));
					this.memory[address] = data;
					return true;
				} else if (table[page].owner.getID() == process.getID()) {
					this.memory[address] = data;
					return true;
				} else {
					Process owner = this.table[page].owner;
					
					this.table[page].owner = process;
					this.table[page].free = false;
					
					if (owner != null) {
						int ownerID = owner.registers[OperatingSystem.PROCESS_ID_REGISTER];
						OperatingSystem.hardDrive.writeTo(new Request(pageStart, pageEnd, ownerID));
					}
					
					OperatingSystem.hardDrive.readFrom(new Request(pageStart, pageEnd, id));
					this.memory[address] = data;
					return true;
				}
			} else {
				// send the CPU a trap
				return false;
			}
		}
	}
	
	private int getPage(int address) {
		return address / OperatingSystem.PAGE_SIZE;
	}
	
	public void requestMemory(Process process, int size) {
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
				return;
			}
		}
		
		int victim = ThreadLocalRandom.current().nextInt(0, memory.length);
		
		if (victim + size > memory.length) {
			victim = memory.length - size;
		}
		
		process.registers[OperatingSystem.PROC_BASE_REGISTER] = victim;
		process.registers[OperatingSystem.PROC_LIMIT_REGISTER] = victim + size;
	}
	
	public void freeMemory(Process process) {
		for (int i = 0; i < table.length; i++) {
			if (table[i].owner == process) {
				table[i].free = true;
				table[i].owner = null;
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