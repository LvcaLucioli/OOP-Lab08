package it.unibo.oop.lab.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();

    /*
     * Once the Controller is done, implement this class in such a way that:
     * 
     * 1) I has a main method that starts the graphical application
     * 
     * 2) In its constructor, sets up the whole view
     * 
     * 3) The graphical interface consists of a JTextField in the upper part of the frame, 
     * a JTextArea in the center and two buttons below it: "Print", and "Show history". 
     * SUGGESTION: Use a JPanel with BorderLayout
     * 
     * 4) By default, if the graphical interface is closed the program must exit
     * (call setDefaultCloseOperation)
     * 
     * 5) The behavior of the program is that, if "Print" is pressed, the
     * controller is asked to show the string contained in the text field on standard output. 
     * If "show history" is pressed instead, the GUI must show all the prints that
     * have been done to this moment in the text area.
     * 
     */

    /**
     * builds a new {@link SimpleGUI}.
     */
    public SimpleGUI() {
        final StringController controller = new StringController();
        /*
         * Make the frame half the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected.
         * 
         * In order to deal coherently with multimonitor setups, other
         * facilities exist (see the Java documentation about this issue). It is
         * MUCH better than manually specify the size of a window in pixel: it
         * takes into account the current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);

        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        
        final JPanel upperPanel = new JPanel(new BorderLayout());
        final JPanel midPanel = new JPanel(new BorderLayout());
        final JPanel bottomPanel = new JPanel(new BorderLayout());
        final JPanel mainPanel = new JPanel(new BorderLayout());
        
        final JTextField stringTextField = new JTextField();
        stringTextField.setEditable(true);
        upperPanel.add(stringTextField);
        
        final JTextArea historyTextArea = new JTextArea();
        historyTextArea.setEditable(false);
        midPanel.add(historyTextArea);
        
        final JButton printButton = new JButton("print");
        final JButton historyButton = new JButton("show history");
        
        bottomPanel.add(printButton, BorderLayout.NORTH);
        bottomPanel.add(historyButton, BorderLayout.SOUTH);
        
        mainPanel.add(upperPanel, BorderLayout.NORTH);
        mainPanel.add(midPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        frame.setContentPane(mainPanel);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        
        printButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.printCurrentString();
            }
        });
        historyButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                historyTextArea.setText(controller.getHistory().toString());
            } 
        });
        stringTextField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                   controller.setString(stringTextField.getText());
            }
        });
    }
    public static void main(final String[] args) {
        final SimpleGUI myGUI = new SimpleGUI();        
    }

}
