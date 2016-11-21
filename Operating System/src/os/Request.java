package os;

public class Request {
	public int startAddress;
	public int endAddress;
	public int processID;
	
	Request(int startAddress, int endAddress, int processID) {
		this.startAddress = startAddress;
		this.endAddress = endAddress;
		this.processID = processID;
	}
	
	@Override
	public int hashCode() {
		return 2 * startAddress
			+ 3 * endAddress
			+ 5 * processID;
	}
	
	@Override
	public boolean equals(Object object) {
		if (object instanceof Request) {
			Request that = (Request) object;
			return this.startAddress == that.startAddress
				&& this.endAddress == that.endAddress
				&& this.processID == that.processID;
		}
		
		return false;
	}
}