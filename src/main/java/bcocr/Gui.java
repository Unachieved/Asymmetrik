package bcocr;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * The standalone gui to be displayed when the program is ran.
 * A gui interface is presented instead of having to use the command line
 */
@SuppressWarnings("serial")
public class Gui extends JFrame {

    //lengths for the gui components to create dimensions
    private static final int BUTTON_HEIGHT = 20;
    private static final int BUTTON_WIDTH = 40;
    private static final int TEXT_PANE_HEIGHT = 200;
    private static final int TEXT_PANE_WIDTH = 300;
    private static final int WINDOW_HEIGHT = 220;
    private static final int WINDOW_WIDTH = 700;

    private JButton parse;
    private JTextArea inPane;
    private JTextArea outList;

    /**
     * This function constructs and initializes the components standalone gui.
     */
    public Gui() {
        super("BusinessCardParser");
        JPanel panel = new JPanel();

        //initialize components for panel
        inPane = new JTextArea();
        outList = new JTextArea();
        parse = new JButton("parse");

        //set the dimensions of the components for the gui
        inPane.setPreferredSize(new Dimension(TEXT_PANE_WIDTH, TEXT_PANE_HEIGHT));
        parse.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        outList.setPreferredSize(new Dimension(TEXT_PANE_WIDTH, TEXT_PANE_HEIGHT));
        panel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        //output should not be editable
        outList.setEditable(false);

        //when clicked send the data entered in the gui to be parsed
        parse.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                outList.setText("");
                ContactInfo tmp = new BusinessCardParser().getContactInfo(inPane.getText());
                outList.append("Name: " + tmp.getName() + "\n");
                outList.append("Phone: " + tmp.getPhoneNumber() + "\n");
                outList.append("Email: " + tmp.getEmailAddress() + "\n");
            }
        });

        //add components to the panel
        panel.add(inPane, BorderLayout.LINE_START);
        panel.add(parse, BorderLayout.CENTER);
        panel.add(outList, BorderLayout.LINE_END);
        add(panel);

        //final gui setup ops
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        pack();
        setVisible(true);
    }
}
