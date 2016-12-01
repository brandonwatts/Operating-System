# Operating-System-Project
CMSC 312 Final Project

# Project Overview and Use

The project can be run through the given jar file. On startup, there will be a "command prompt window" for entering commands into the operating system. The commands relevant for seeing operating system diagnostic information are: proc, tskmgr, mem, and memgraph. The command proc will print out all process control block information as well as current CPU register values. The command tskmgr will display the task manager window, which shows memory usage and register values for all currently loaded processes. The command mem will print out the current memory usage, and the command memgraph will open a window displaying a graph with respect to time of memory usage by the operating system.

The other commands are load, exe, and reset. The command load will load a given program or job file into the operating system. If you're loading a job file, simpling add the job file's name on the same line after the load command. However, for a program file, you must include another argument after the file name, which is the clock cycle after which you want the program to be loaded into the operating system. The exe command will run the operating system for a single clock cycle, and if enter an integer value after the exe command, it will run for however many clock cycles you entered. Finally, the reset command will reset the operating system back to its starting state.

List of commands:

proc, mem, load, exe, reset, tskmgr, memgraph

Example usage:

tskmgr
memgraph
load simulation.job
exe
exe
exe
proc
mem
exe 50
exe 50
exe 50
proc
load wordprocessor.program 0
load game.program 200
exe 25
exe 25
proc
exe 25
exe 25
proc

When a process is loaded into the operating system, enough contiguous pages are allocated to the process in order to satisfy it memory requirments. The instructions for the process are also loaded into the start of the memory given to the process. After having been loaded into the operating system, a process will be scheduled using round robin scheduling with a quantum of 10 cycles. If a process executes an IO instruction it will be be put into a waiting queue, waiting until the IO device sends an interrupt to the CPU, at which point, the process will return to the ready state and eventually be chosen by the scheduler. After a process terminates, its memory will be marked as free by the system and allocated to new processes as need be.

The operating system also uses a mock hard drive in order to support page swapping and virtual memory. Although there are only 256 kilobytes in main memory, the hard drive has no constraint, allowing an arbitrary number of processes to run at once. However, no single process can request more than 256 kilobytes of memory, and any request to load such a process into the operating system is ignored.

There were also extra instructions added in, which allow processes to be Turing complete by navigating and modifying their own memory. Two program files, "helloworld.program" and "fizzbuzz.program" use these extra instructions and can be run. Their output will be displayed in the command prompt window.
