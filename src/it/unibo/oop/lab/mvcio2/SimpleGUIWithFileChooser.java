package it.unibo.oop.lab.mvcio2;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.BorderLayout;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import it.unibo.oop.lab.mvcio.Controller;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {
    private final JFrame frame = new JFrame();
    /*
     * TODO: Starting from the application in mvcio:
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface.
     * Suggestion: use a second JPanel with a second BorderLayout, put the panel
     * in the North of the main panel, put the text field in the center of the
     * new panel and put the button in the line_end of the new panel.
     * 
     * 2) The JTextField should be non modifiable. And, should display the
     * current selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should
     * use the method showSaveDialog() to display the file chooser, and if the
     * result is equal to JFileChooser.APPROVE_OPTION the program should set as
     * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
     * then the program should do nothing. Otherwise, a message dialog should be
     * shown telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to
     * update the UI: in this example the UI knows when should be updated, so
     * try to keep things separated.
     */

    public SimpleGUIWithFileChooser() {
        final Controller controller = new Controller();
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        
        final JPanel mainPanel = new JPanel(new BorderLayout());
        final JPanel secondPanel = new JPanel(new BorderLayout());
        
        final JTextField currentFileTextField = new JTextField(controller.getCurrentFilePath());
        currentFileTextField.setEditable(false);
        
        final JButton browseButton = new JButton("browse");
        final JTextArea textArea = new JTextArea();
        final JButton saveButton = new JButton("save");
        
        mainPanel.add(secondPanel, BorderLayout.NORTH);
        mainPanel.add(textArea, BorderLayout.CENTER);
        mainPanel.add(saveButton, BorderLayout.SOUTH);
        
        secondPanel.add(currentFileTextField, BorderLayout.CENTER);
        secondPanel.add(browseButton, BorderLayout.LINE_END);
        
        browseButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser fileChooser = new JFileChooser();
                final int returnResult = fileChooser.showSaveDialog(frame);
                if (returnResult == JFileChooser.APPROVE_OPTION) {
                    controller.setCurrentFile(fileChooser.getSelectedFile());
                    currentFileTextField.setText(controller.getCurrentFilePath());
                }
                if (returnResult == JFileChooser.ERROR_OPTION) {
                    JOptionPane.showMessageDialog(fileChooser, e);
                }
            }
        });
        
        saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.writeAString(textArea.getText());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } 
        });
        
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }
    
    public static void main(final String[] args) {
        final SimpleGUIWithFileChooser myGUI = new SimpleGUIWithFileChooser();
    }

}
