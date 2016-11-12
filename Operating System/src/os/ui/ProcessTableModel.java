package os.ui;

import os.ProcessData;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;


public class ProcessTableModel extends AbstractTableModel {

    private final String[] columnNames = {"Process", "Memory Useage"};
    public List<ProcessData> processList;

    public ProcessTableModel() {
        processList = new ArrayList<>();
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
        ProcessData processData = processList.get(rowIndex);
        switch (columnIndex) {
            case (0):
                return processData.name;
            case (1):
                return processData.memory;
        }
        return null;
    }

    public void addProcessData(final ProcessData contact) {
        processList.add(contact);
        fireTableDataChanged();
    }

    public void removeProcessData(final ProcessData processData) {
        processList.remove(processData);
        fireTableDataChanged();
    }


}