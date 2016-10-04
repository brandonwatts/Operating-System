/**Used http://www.gitam.edu/eresource/comp/gvr(os)/4.2.htm as reference **/


import java.util.Deque;
import java.util.LinkedList;

public class ExecutionQueue {
	
	Deque<Process> executionQueue;
	
	public ExecutionQueue()
	{
		executionQueue = new LinkedList<Process>();		
	}
	
	public boolean enQueue(Process process)
	{
		return executionQueue.add(process);
	}
	
	public void deQueue()
	{
		executionQueue.remove();
	}
	
	public int getnumberofProcesses(){return executionQueue.size();}
	
}
