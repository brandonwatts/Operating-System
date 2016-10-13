package os.interrupt;

import java.util.EventListener;

/** InteruptProcessorListener is a special listener that waits for interrupts **/
interface InterruptProcessorListener extends EventListener {
	public void interruptOccurred(Interrupt evt);
}
