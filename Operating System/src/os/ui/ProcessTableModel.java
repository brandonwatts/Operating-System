package os.ui;

import os.OperatingSystem;
import os.Process;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ProcessTableModel extends AbstractTableModel {

    private final String[] columnNames = {"Process", "Memory Useage","Register 1","Register 2","Register 3","Register 4"};
    public List<Process> processList;
    public int numberProcesses;

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
            case (2):
                return process.registers[0];
            case (3):
                return process.registers[1];
            case (4):
                return process.registers[2];
            case(5):
                return process.registers[3];
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