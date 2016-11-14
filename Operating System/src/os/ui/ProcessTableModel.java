package os.ui;

import os.OperatingSystem;
import os.Process;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ProcessTableModel extends AbstractTableModel {

    private final String[] columnNames = {"Process", "Memory Useage"};
    public List<Process> processList;
    public int numberProcesses ;

    public ProcessTableModel() {
        processList = new ArrayList<>();
        numberProcesses = 0;
    }

    @Override
    public int getRowCount() {
        return processList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Process process = processList.get(rowIndex);
        switch (columnIndex) {
            case (0):
                return process.getName();
            case (1):
                return process.getMemoryUseage();
        }
        return null;
    }

    public void addProcessData(final Process process) {
        processList.add(process);
        OperatingSystem.taskManager.getNumberOfProcesses().setText(Integer.toString(++numberProcesses));
        fireTableDataChanged();
    }

    public void removeProcessData(final Process process) {
        processList.remove(process);
        OperatingSystem.taskManager.getNumberOfProcesses().setText(Integer.toString(--numberProcesses));
        fireTableDataChanged();
    }


}