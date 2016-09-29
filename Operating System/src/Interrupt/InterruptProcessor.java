package Interrupt;
import javax.swing.event.EventListenerList;

/** Class that handles the interrupts as they are generated **/
class InterruptProcessor {
	
	/** List that holds the listeners from all the classes **/
	protected EventListenerList listenerList = new EventListenerList();
	
	/** Adds an event listener to the list **/
	public void addMyEventListener(InterruptProcessorListener listener) {
		listenerList.add(InterruptProcessorListener.class, listener);
	}
	
	/** Removes a listener **/
	public void removeMyEventListener(InterruptProcessorListener listener) {
		listenerList.remove(InterruptProcessorListener.class, listener);
	}
	
	/** Method used to fire an Interrupt **/
	void fireInterrupt(Interrupt evt) {
		Object[] listeners = listenerList.getListenerList();
		for (int i = 0; i < listeners.length; i = i+2) {
			if (listeners[i] == InterruptProcessorListener.class) {
				((InterruptProcessorListener) listeners[i+1]).interruptOccurred(evt);
			}
		}
	}
}
