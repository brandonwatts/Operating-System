import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.JSeparator;

public class TaskManager {

	JFrame frame;
	JTextPane processDescription;
	Border paddingBorder = BorderFactory.createEmptyBorder(15,15,15,15);

	/**
	 * Create the application.
	 */
	public TaskManager() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
	
		JPanel activeProcesses = new JPanel(new BorderLayout());
		DefaultListModel<String> processes = new DefaultListModel<>();
		for(int i=0;i<20;i++)
		{
			processes.addElement("EXAMPLE");
		}
		
		JList list = new JList(processes);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(10);
		list.setFixedCellHeight(50);
		list.setFixedCellWidth(100);
		ListSelectionModel listSelectionModel = list.getSelectionModel();
		ListSelectionListener processSelectionModel = new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent listSelectionEvent) {
		        boolean adjust = listSelectionEvent.getValueIsAdjusting();
		        if (!adjust) {
		          JList list = (JList) listSelectionEvent.getSource();
		          Object selectionValue = list.getSelectedValue();
		          processDescription.setText("EXAMPLE PCB INFORMATION FOR SELECTION: " + list.getSelectedIndex());
		        }
		      }
		    };
		    list.addListSelectionListener(processSelectionModel);

		
		DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(list);
		JLabel activeProcessesTitle = new JLabel();
		activeProcessesTitle.setText("Active Processes");
		activeProcessesTitle.setFont(activeProcessesTitle.getFont().deriveFont(16f));
		activeProcessesTitle.setBorder(paddingBorder);
		activeProcessesTitle.setHorizontalAlignment(SwingConstants.CENTER);
		activeProcesses.add(activeProcessesTitle,BorderLayout.NORTH);
		activeProcesses.add(scrollPane,BorderLayout.CENTER);
		frame.getContentPane().add(activeProcesses, BorderLayout.WEST);
		

		
		
		JPanel processDescriptionBox = new JPanel(new BorderLayout());
		
		processDescription = new JTextPane();
		processDescription.setEditable(false);
		JLabel processDescriptionTitle = new JLabel();
		processDescriptionTitle.setText("Process Description");
		processDescriptionTitle.setFont(activeProcessesTitle.getFont().deriveFont(16f));
		processDescriptionTitle.setBorder(paddingBorder);
		processDescriptionTitle.setHorizontalAlignment(SwingConstants.CENTER);
		processDescriptionBox.add(processDescriptionTitle,BorderLayout.NORTH);
		processDescriptionBox.add(processDescription,BorderLayout.CENTER);
		frame.getContentPane().add(processDescriptionBox, BorderLayout.CENTER);
		
		
		

		JPanel statistics = new JPanel(new GridLayout(0,1));
		
		
		
		frame.getContentPane().add(statistics, BorderLayout.EAST);

		
		JLabel freeMemoryLabel = new JLabel("Memory Left: " + Integer.toString(Memory.getFreeMemory()));
		freeMemoryLabel.setBorder(paddingBorder);
		statistics.add(freeMemoryLabel);
		
		JLabel usedMemoryLabel = new JLabel("Memory Used: " +Integer.toString(Memory.getUsedMemory()));
		usedMemoryLabel.setBorder(paddingBorder);
		statistics.add(usedMemoryLabel);
		
		JLabel activeProcessesLabel = new JLabel("Number of Active Processes: " + Integer.toString(ExecutionQueue.getnumberofProcesses()));
		activeProcessesLabel.setBorder(paddingBorder);
		statistics.add(activeProcessesLabel);

		frame.setVisible(true);
	}
	
	public int populateFreeMemory()
	{
		return Memory.getFreeMemory();
	}
	
}
