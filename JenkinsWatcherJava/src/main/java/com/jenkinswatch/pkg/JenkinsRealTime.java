package com.jenkinswatch.pkg;

import com.lowlevelcommunication.pkg.EmbeddedDevice;
import com.offbytwo.jenkins.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TimerTask;

/**
 * Created by asebak on 9/22/2014.
 */
public class JenkinsRealTime extends TimerTask{
    private ArrayList<Job> selectedJobs;
    private EmbeddedDevice embeddedDevice = new EmbeddedDevice();
    private static final char BUILD_UNSTABLE = '0';
    private static final char BUILD_BUILDING = '1';
    private static final char BUILD_SUCCESS = '2';
    public JenkinsRealTime(ArrayList<Job> selectedJobs){
        this.selectedJobs = selectedJobs;
    }
    @Override
    public void run() {
        System.out.println("watch dog called");
        for(Job job: this.selectedJobs){
            try {
                    JobWithDetails jobWithDetails = job.details();
                    Build build = jobWithDetails.getLastBuild();
                    BuildWithDetails buildWithDetails = build.details();
                    BuildResult buildResult =  buildWithDetails.getResult();
                    if(buildResult == null){
                        buildResult = BuildResult.BUILDING;
                    }
                    switch (buildResult){
                        case FAILURE:
                            break;
                        case UNSTABLE:
                            break;
                        case REBUILDING:
                            break;
                        case BUILDING:
                            break;
                        case ABORTED:
                            break;
                        case SUCCESS:
                            break;
                        case UNKNOWN:
                            break;
                    }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
