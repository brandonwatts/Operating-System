package os;

import os.ui.ProcessTableModel;

/**
 * Class to store Helper Methods
 */
public class Tasks {
    public static void updateTaskManager(){
        ((ProcessTableModel)OperatingSystem.taskManager.getTable().getModel()).fireTableDataChanged();

    }
    public static void refreshStats(){
        OperatingSystem.taskManager.refreshStatistics();
    }
}
