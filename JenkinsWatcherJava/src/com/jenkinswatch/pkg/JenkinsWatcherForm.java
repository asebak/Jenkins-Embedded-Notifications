package com.jenkinswatch.pkg;

import javax.swing.*;

/**
 * Created by asebak on 9/21/2014.
 */
public class JenkinsWatcherForm extends JFrame {
    private JPanel jPanel;
    private JTextField serverField;
    private JButton refreshBtn;

    public JenkinsWatcherForm(){
        super("Jenkins Real-Time Build Monitor");
        this.setContentPane(jPanel);
        this.pack();
        this.setVisible(true);
    }
}
