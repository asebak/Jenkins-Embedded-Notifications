package com.jenkinswatch.pkg;

/**
 * Created by asebak on 9/21/2014.
 */
public class JenkinsOverview {
    private String url;

    public JenkinsOverview(String url){
        this.url = url + "api/json";
    }

    public String getUrl() {
        return url;
    }
}
