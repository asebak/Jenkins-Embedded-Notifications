package com.jenkinswatch.pkg;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by asebak on 9/21/2014.
 */
public class JenkinsWatcherForm extends JFrame {
    private JPanel jPanel;
    private JTextField serverField;
    private JButton refreshBtn;
    private JTable jTable;

    public JenkinsWatcherForm(){
        super("Jenkins Real-Time Build Monitor");
        this.setContentPane(this.jPanel);
        this.refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String serverUrl = serverField.getText();
                if(serverUrl.charAt(serverUrl.length() -1) != '/'){
                    serverUrl += "/";
                }
                JenkinsOverview jenkinsOverview = new JenkinsOverview(serverUrl);
                JenkinsApi.getRequest(jenkinsOverview.getUrl());
            }
        });
        this.initializeJenkinsServerTable();
        this.pack();
        this.setVisible(true);
    }

    public void initializeJenkinsServerTable(){
        String[] colHeaders = {"Name", "Build Status", "Build Url"};
        DefaultTableModel model = new DefaultTableModel(null, colHeaders);
        this.jTable.setModel(model);
    }
}
