package os.interrupt;

import java.util.EventObject;

/** Interface you must create **/
public interface InterruptProcessorInterface {
	public void handleInterruptEvent(EventObject e);
}