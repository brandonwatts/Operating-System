package os.ui;

import os.OperatingSystem;
import os.Process;

import javax.swing.*;
import java.awt.*;

public class TaskManager extends JFrame {

    private JTable contactTable;
    private JScrollPane scrollPane;
    private JLabel numberOfProcesses;

    public TaskManager() {
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setTitle("Task Manager");
        contactTable = new JTable(new ProcessTableModel());
        scrollPane = new JScrollPane(contactTable);

        JPanel infoPane = new JPanel();
        infoPane.setLayout(new FlowLayout());
        infoPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        JLabel numberOfProcessesLabel = new JLabel("Number of Processes: ");
        numberOfProcesses = new JLabel("0");
        infoPane.add(numberOfProcessesLabel);
        infoPane.add(numberOfProcesses);

        this.getContentPane().add(scrollPane);
        this.getContentPane().add(infoPane);
        this.setSize(new Dimension(400, 600));
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

}