public class Memory {
	
	private static int free = 0;
	private static int used = 0;
	
	public Memory(int capacity) {
		free = capacity;
	}
	
	public static int getFreeMemory() {
		return free;
	}
	
	public static int getUsedMemory() {
		return used;
	}
	
	public static int getCapacity() {
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