import java.util.EventListener;

interface InterruptProcessorListener extends EventListener {
  public void interruptOccurred(Interrupt evt);
}