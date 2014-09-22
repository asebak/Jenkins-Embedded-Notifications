package com.jenkinswatch.pkg;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private JButton monitorBtn;
    private JenkinsServer jenkinsServer;
    private Map<String, Job> jenkinsJobs;
    public JenkinsWatcherForm(){
        super("Jenkins Real-Time Build Monitor");
        this.setContentPane(this.jPanel);
        this.setButtonEvents();
        //this.setTableEvents();
        this.pack();
        this.setVisible(true);
    }
    public void setButtonEvents(){
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
        this.monitorBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(monitorBtn.getText() == "Stop Monitoring"){
                    monitorBtn.setText("Monitor");

                }else{
                    monitorBtn.setText("Stop Monitoring");
                }

            }
        });
    }
    @Deprecated
    public void setTableEvents(){
        this.jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    Job job = jenkinsJobs.get(row);
                }
            }
        });
    }
    public void initializeJenkinsServerTable(){
        String[] colHeaders = {"Monitor", "Name", "Build Url"};
        DefaultTableModel model = new DefaultTableModel(null, colHeaders){
            @Override
            public Class getColumnClass(int columnIndex) {
                return getValueAt(0, columnIndex).getClass();
            }
            @Override
            public  boolean isCellEditable(int row, int column) {
                return (column == 0);
            }
        };
        for (Job job : jenkinsJobs.values()) {
            Object[] jobRow = {Boolean.FALSE, job.getName(),job.getUrl()};
            model.addRow(jobRow);
        }
        this.jTable.setModel(model);
    }
}
