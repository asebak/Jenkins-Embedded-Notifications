package com.jenkinswatch.pkg;

import com.offbytwo.jenkins.JenkinsServer;

import java.util.ArrayList;
import java.util.TimerTask;

/**
 * Created by asebak on 9/22/2014.
 */
public class JenkinsRealTime extends TimerTask{
    private ArrayList<String> jobs;
    private JenkinsServer jenkinsServer;
    public JenkinsRealTime(JenkinsServer jenkinsServer, ArrayList<String> jobs){
        //super.cancel();
        this.jobs = jobs;
        this.jenkinsServer = jenkinsServer;
    }
    @Override
    public void run() {
        System.out.println("watch dog called");
    }
}
