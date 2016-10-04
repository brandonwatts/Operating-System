import java.awt.EventQueue;

public class TaskManagerInitializer {
	
	public TaskManagerInitializer(OperatingSystem os){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaskManager window = new TaskManager(os);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
