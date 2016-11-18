package os.ui;

import os.OperatingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Prompt {

    int queuePosition = 0;
    private JFrame frame;
    private JTextField commandLine;
    private Font frameFont;
    private JTextArea output;
    final static int MAX_COMMAND_LENGTH = 80;
    final static int OUTPUT_HEIGHT = 20;
    final static int OUTPUT_WIDTH = 80;
    final static int FONT_SIZE = 20;

    public Prompt() {
        init();
    }

    private void centerWindow() {
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = defaultToolkit.getScreenSize();
        int x = (int) ((screenSize.getWidth() / 2) - (frame.getWidth() / 2));
        int y = (int) ((screenSize.getHeight() / 2) - (frame.getHeight() / 2));
        frame.setLocation(x, y);
    }

    public void output(final String string) {
        output.setText(string);
    }

    final public void writeCommand(final String command) {
        commandLine.setText(command);
    }

    public void append(String string) {
        output.append(string);
    }

    public void init() {
        frameFont = new Font("Consolas", Font.BOLD, FONT_SIZE);
        frame = new JFrame("Command Prompt");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.add(initPanel());
        frame.pack();
        centerWindow();
    }

    /**
     * Helper method to initialize our JPanel.
     *
     * @return JPanel for our Command Prompt
     */
    private JPanel initPanel() {

        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(initCommandLine(), constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        panel.add(initOutput(), constraints);

        return panel;
    }

    /**
     * Helper method to create the command line and attach listeners.
     *
     * @return Command line as a JTextField
     */
    private JTextField initCommandLine() {
        commandLine = new JTextField(MAX_COMMAND_LENGTH);
        commandLine.setFont(frameFont);

        commandLine.addKeyListener(new KeyListener() {
            public void keyTyped(final KeyEvent e) {
            }

            public void keyPressed(final KeyEvent e) {
                int keyPressed = e.getKeyCode();
                if (keyPressed == KeyEvent.VK_UP) {
                    queuePosition++;
                    if (!OperatingSystem.getCommandQueue().isEmpty()) {
                        String command = (String) OperatingSystem.getCommandQueue().get(Math.abs(queuePosition) % OperatingSystem.getCommandQueue().size());
                        OperatingSystem.prompt.writeCommand(command.trim());
                    }
                }
                if (keyPressed == KeyEvent.VK_DOWN) {
                    if (!OperatingSystem.getCommandQueue().isEmpty()) {
                        queuePosition--;
                        String command = (String) OperatingSystem.getCommandQueue().get(Math.abs(queuePosition) % OperatingSystem.getCommandQueue().size());
                        OperatingSystem.prompt.writeCommand(command.trim());
                    }
                }
            }

            public void keyReleased(final KeyEvent e) {
            }
        });

        commandLine.addActionListener(event -> {
            String string = commandLine.getText();
            output.append(string + "\n");
            commandLine.setText("");
            OperatingSystem.process(string);
        });

        return commandLine;
    }

    /**
     * Helper Method to generate the Output window.
     *
     * @return The Output window.
     */
    private JScrollPane initOutput() {
        output = new JTextArea(OUTPUT_HEIGHT, OUTPUT_WIDTH);
        output.setEditable(false);
        output.setFont(frameFont);
        output.setLineWrap(true);
        output.setWrapStyleWord(true);
        return new JScrollPane(output);
    }

}
