using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JenkinsWatcher
{
    public class JenkinsJobOverview
    {
        public List<object> actions { get; set; }
        public object description { get; set; }
        public string displayName { get; set; }
        public object displayNameOrNull { get; set; }
        public string name { get; set; }
        public string url { get; set; }
        public bool buildable { get; set; }
        public List<object> builds { get; set; }
        public string color { get; set; }
        public object firstBuild { get; set; }
        public List<object> healthReport { get; set; }
        public bool inQueue { get; set; }
        public bool keepDependencies { get; set; }
        public object lastBuild { get; set; }
        public object lastCompletedBuild { get; set; }
        public object lastFailedBuild { get; set; }
        public object lastStableBuild { get; set; }
        public object lastSuccessfulBuild { get; set; }
        public object lastUnstableBuild { get; set; }
        public object lastUnsuccessfulBuild { get; set; }
        public int nextBuildNumber { get; set; }
        public List<object> property { get; set; }
        public object queueItem { get; set; }
        public bool concurrentBuild { get; set; }
        public List<object> downstreamProjects { get; set; }
        public Scm scm { get; set; }
        public List<object> upstreamProjects { get; set; }
    }
    public class Build
    {
        public int number { get; set; }
        public string url { get; set; }
    }

    public class FirstBuild
    {
        public int number { get; set; }
        public string url { get; set; }
    }

    public class HealthReport
    {
        public string description { get; set; }
        public string iconClassName { get; set; }
        public string iconUrl { get; set; }
        public int score { get; set; }
    }

    public class LastBuild
    {
        public int number { get; set; }
        public string url { get; set; }
    }

    public class LastCompletedBuild
    {
        public int number { get; set; }
        public string url { get; set; }
    }

    public class LastStableBuild
    {
        public int number { get; set; }
        public string url { get; set; }
    }

    public class LastSuccessfulBuild
    {
        public int number { get; set; }
        public string url { get; set; }
    }
    public class Scm
    {
    }

}
