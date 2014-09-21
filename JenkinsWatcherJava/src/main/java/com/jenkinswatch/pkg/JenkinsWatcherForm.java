package com.jenkinswatch.pkg;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * Created by asebak on 9/21/2014.
 */
public class JenkinsWatcherForm extends JFrame {
    private JPanel jPanel;
    private JTextField serverField;
    private JButton refreshBtn;
    private JTable jTable;
    private JenkinsServer jenkinsServer;
    private Map<String, Job> jenkinsJobs;
    public JenkinsWatcherForm(){
        super("Jenkins Real-Time Build Monitor");
        this.setContentPane(this.jPanel);
        this.refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String serverUrl = serverField.getText();
                try {
                    jenkinsServer = new JenkinsServer(new URI(serverUrl));
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }
                try {
                    jenkinsJobs = jenkinsServer.getJobs();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                initializeJenkinsServerTable();
            }
        });
        this.pack();
        this.setVisible(true);
    }

    public void initializeJenkinsServerTable(){
        String[] colHeaders = {"Name", "Build Url"};
        DefaultTableModel model = new DefaultTableModel(null, colHeaders);
        for (Job job : jenkinsJobs.values()) {
            Object[] newRowData = {job.getName(),job.getUrl()};
            model.addRow(newRowData);
        }
        this.jTable.setModel(model);
    }
}
