package os;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HardDrive {
	private HashMap<Request, Object[]> memory;
	
	public HardDrive() {
		this.memory = new HashMap<Request, Object[]>();
	}
	
	public void writeTo(Request request) {
		Object[] data = new Object[OperatingSystem.PAGE_SIZE];
		
		for (int i = 0; i < data.length; i++) {
			data[i] = OperatingSystem.memory.read(request.startAddress + i);
		}
		
		memory.put(request, data);
	}
	
	public void readFrom(Request request) {
		Object[] data = memory.get(request);
		
		if (data == null) {
			for (int i = 0; i < OperatingSystem.PAGE_SIZE; i++) {
				OperatingSystem.memory.write(request.startAddress + i, null);
			}
		} else {
			for (int i = 0; i < OperatingSystem.PAGE_SIZE; i++) {
				OperatingSystem.memory.write(request.startAddress + i, data[i]);
			}
		}
	}
	
	public void freeMemory(int id) {
		Iterator<Map.Entry<Request, Object[]>> it = memory.entrySet().iterator();
		while (it.hasNext()) {
			if (it.next().getKey().processID == id) {
				it.remove();
			}
		}
	}
}
