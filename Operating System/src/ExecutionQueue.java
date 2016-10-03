/**Used http://www.gitam.edu/eresource/comp/gvr(os)/4.2.htm as reference **/


import java.util.Deque;
import java.util.LinkedList;

public class ExecutionQueue {
	
	static Deque<Process> executionQueue = new LinkedList<Process>();
	
	public ExecutionQueue(Process... processes)
	{
		
		if(processes.length==0)
			throw new IllegalStateException();
		
		for(int i=0; i<processes.length;i++)
		{
			this.enQueue(processes[i]);
		}
		
	}
	
	public boolean enQueue(Process process)
	{
		return executionQueue.add(process);
	}
	
	public void deQueue()
	{
		executionQueue.remove();
	}
	
	public static int getnumberofProcesses(){return executionQueue.size();}
	
}
