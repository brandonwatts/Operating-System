package os.ui;

import javax.swing.*;
import java.awt.*;

public class TaskManager extends JFrame {

    private JTable contactTable;
    private JScrollPane scrollPane;

    public TaskManager() {

        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setTitle("Task Manager");
        contactTable = new JTable(new ProcessTableModel());
        scrollPane = new JScrollPane(contactTable);
        this.getContentPane().add(scrollPane);
        this.setSize(new Dimension(400, 600));
        this.setVisible(false);
    }

    public JTable getTable() {
        return contactTable;
    }


}