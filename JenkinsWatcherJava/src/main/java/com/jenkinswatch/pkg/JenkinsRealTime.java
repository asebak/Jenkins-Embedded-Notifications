package com.jenkinswatch.pkg;

import com.lowlevelcommunication.pkg.HardwareAbstractionLayer;
import com.offbytwo.jenkins.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;

/**
 * Created by asebak on 9/22/2014.
 */
public class JenkinsRealTime extends TimerTask{
    private ArrayList<Job> selectedJobs;
    private HardwareAbstractionLayer hardwareAbstractionLayer = new HardwareAbstractionLayer();
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
                    //Fix for bug reported on github
                    if(buildResult == null){
                        buildResult = BuildResult.BUILDING;
                    }
                    switch (buildResult){
                        case FAILURE:
                            hardwareAbstractionLayer.send(BUILD_UNSTABLE);
                            break;
                        case UNSTABLE:
                            hardwareAbstractionLayer.send(BUILD_UNSTABLE);
                            break;
                        case REBUILDING:
                            hardwareAbstractionLayer.send(BUILD_BUILDING);
                            break;
                        case BUILDING:
                            hardwareAbstractionLayer.send(BUILD_BUILDING);
                            break;
                        case ABORTED:
                            break;
                        case SUCCESS:
                            hardwareAbstractionLayer.send(BUILD_SUCCESS);
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
