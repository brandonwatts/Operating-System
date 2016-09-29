import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.function.Consumer;

public class Prompt {
    private JFrame frame;
    private JPanel panel;
    private JTextArea textArea;
    private JTextField textField;
    
    public Prompt(Consumer<String> consumer) {
        panel = new JPanel(new GridBagLayout());
        Font font = new Font("Consolas", Font.BOLD, 20);
        
        textField = new JTextField(80);
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String string = textField.getText();
                textArea.append(string + "\n");
                textField.setText("");
                consumer.accept(string);
            }
        });
        textField.setFont(font);
        
        textArea = new JTextArea(20, 80);
        textArea.setEditable(false);
        textArea.setFont(font);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(textField, c);
        
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        panel.add(scrollPane, c);
        
        frame = new JFrame("Command Prompt");
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        centerWindow(frame);
        frame.setVisible(true);
    }
    
    private void centerWindow(JFrame frame) {
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = defaultToolkit.getScreenSize();
		int x = (int) ((screenSize.getWidth() / 2) - (frame.getWidth() / 2));
		int y = (int) ((screenSize.getHeight() / 2) - (frame.getHeight() / 2)); 
        frame.setLocation(x, y);
	}
    
    public void setText(String string) {
		textArea.setText(string);
	}
	
	public void append(String string) {
		textArea.append(string);
	}
    
}
