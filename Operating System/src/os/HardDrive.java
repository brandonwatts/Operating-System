package os;

import java.util.ArrayList;
import java.util.HashMap;

public class HardDrive {
	private ArrayList<Request> readRequests;
	private ArrayList<Request> writeRequests;
	
	private HashMap<Request, Object[]> memory;
	
	public HardDrive() {
		this.readRequests = new ArrayList<>();
		this.writeRequests = new ArrayList<>();
		this.memory = new HashMap<>();
	}
	
	public void writeTo(Request request) {
		System.out.println("writeTo");
		System.out.println("start " + request.startAddress);
		System.out.println("end " + request.endAddress);
		System.out.println("id " + request.processID);
		
		Object[] data = new Object[OperatingSystem.PAGE_SIZE];
		
		for (int i = 0; i < data.length; i++) {
			data[i] = OperatingSystem.memory.read(request.startAddress + i);
		}
		
		memory.put(request, data);
	}
	
	public void readFrom(Request request) {
		System.out.println("readFrom");
		System.out.println("start " + request.startAddress);
		System.out.println("end " + request.endAddress);
		System.out.println("id " + request.processID);
		
		Object[] data = memory.get(request);
		
		if (data == null) {
			System.out.println("NULLSHIT");
			for (int i = 0; i < OperatingSystem.PAGE_SIZE; i++) {
				OperatingSystem.memory.write(request.startAddress + i, null);
			}
		} else {
			System.out.println("REALDATA?");
			for (int i = 0; i < OperatingSystem.PAGE_SIZE; i++) {
				OperatingSystem.memory.write(request.startAddress + i, data[i]);
			}
		}
	}
	
	public void freeMemory(int id) {
		for (Request request : memory.keySet()) {
			if (request.processID == id) {
				memory.remove(request);
			}
		}
	}
}
