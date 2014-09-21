package com.jenkinswatch.pkg;

import javax.swing.*;

/**
 * Created by asebak on 9/21/2014.
 */
public class JenkinsWatcherForm extends JFrame {
    private JTextField textField1;
    private JPanel rootPanel;

    public JenkinsWatcherForm(){
        super("Jenkins Real-Time Build Monitor");
        this.setContentPane(rootPanel);
        this.pack();
        this.setVisible(true);
    }
}
