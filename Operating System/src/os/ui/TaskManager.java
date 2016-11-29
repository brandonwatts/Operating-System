package os.ui;

import os.OperatingSystem;
import os.Process;

import javax.swing.*;
import java.awt.*;

public class TaskManager extends JFrame {

    private JTable contactTable;
    private JScrollPane scrollPane;
    private JLabel numberOfProcesses;
    private JLabel amountOfFreeMemory;
    private JLabel amountOfUsedMemory;

    public TaskManager() {
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setTitle("Task Manager");
        contactTable = new JTable(new ProcessTableModel());
        scrollPane = new JScrollPane(contactTable);

        JPanel infoPane = new JPanel();
        infoPane.setLayout(new BoxLayout(infoPane,BoxLayout.LINE_AXIS));
        JLabel numberOfProcessesLabel = new JLabel("Number of Processes: ");
        numberOfProcesses = new JLabel("0");
        infoPane.add(numberOfProcessesLabel);
        infoPane.add(numberOfProcesses);

        JLabel memoryinSystemLabel = new JLabel("Free Memory: ");
        amountOfFreeMemory = new JLabel(Integer.toString(OperatingSystem.memory.getFreeMemory()));
        memoryinSystemLabel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 0));
        infoPane.add(memoryinSystemLabel);
        infoPane.add(amountOfFreeMemory);

        JLabel usedMemoryLabel = new JLabel("Used Memory: ");
        amountOfUsedMemory = new JLabel(Integer.toString(OperatingSystem.memory.getUsedMemory()));
        usedMemoryLabel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 0));
        infoPane.add(usedMemoryLabel);
        infoPane.add(amountOfUsedMemory);

        this.getContentPane().add(scrollPane);
        this.getContentPane().add(infoPane);
        this.setSize(new Dimension(700, 600));
        this.setVisible(false);
    }

    public JTable getTable() {
        return contactTable;
    }

    public JLabel getNumberOfProcesses(){ return numberOfProcesses;}

    public void removeFromModel(Process process)
    {
        ProcessTableModel model = (ProcessTableModel) OperatingSystem.taskManager.getTable().getModel();
        model.removeProcessData(process);
    }
    public void addToModel(Process process)
    {
        ProcessTableModel model = (ProcessTableModel) OperatingSystem.taskManager.getTable().getModel();
        model.addProcessData(process);
    }

    public void refreshStatistics()
    {
        amountOfFreeMemory.setText(Integer.toString(OperatingSystem.memory.getFreeMemory()));
        amountOfUsedMemory.setText(Integer.toString(OperatingSystem.memory.getUsedMemory()));
    }

    public static void updateTaskManager(){
        ((ProcessTableModel)OperatingSystem.taskManager.getTable().getModel()).fireTableDataChanged();

    }

}