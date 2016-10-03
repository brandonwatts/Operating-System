import java.awt.EventQueue;

public class TaskManagerInitializer {
	
	public TaskManagerInitializer(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaskManager window = new TaskManager();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
