/** Enumeration of the process states for easier readability **/
public enum ProcessStates {
NEW,   // The program or process is being created or loaded (but not yet in memory).
READY, // The program is loaded into memory and is waiting to run on the CPU.
RUN,   // Instructions are being executed (or simulated).
WAIT,  // The program is waiting for some event to occur (such as an I/O completion).
EXIT;  // The program has finished execution on the CPU (all instructions and I/O complete) and leaves memory.
}
