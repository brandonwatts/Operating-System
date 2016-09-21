
import javax.swing.event.EventListenerList;


class InterruptProcessor {
  protected EventListenerList listenerList = new EventListenerList();

  public void addMyEventListener(InterruptProcessorListener listener) {
    listenerList.add(InterruptProcessorListener.class, listener);
  }
  public void removeMyEventListener(InterruptProcessorListener listener) {
    listenerList.remove(InterruptProcessorListener.class, listener);
  }
  void fireMyEvent(Interrupt evt) {
    Object[] listeners = listenerList.getListenerList();
    for (int i = 0; i < listeners.length; i = i+2) {
      if (listeners[i] == InterruptProcessorListener.class) {
        ((InterruptProcessorListener) listeners[i+1]).interruptOccurred(evt);
      }
    }
  }
}
