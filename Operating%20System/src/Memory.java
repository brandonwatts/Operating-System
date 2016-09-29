public class Memory {
	
	private int free = 0;
	private int used = 0;
	
	public Memory(int capacity) {
		this.free = capacity;
	}
	
	public int getFreeMemory() {
		return free;
	}
	
	public int getUsedMemory() {
		return used;
	}
	
	public int getCapacity() {
		return free + used;
	}
	
	public boolean requestMemory(int size) {
		if (size <= free) {
			free -= size;
			used += size;
			return true;
		}
		
		return false;
	}
}