#region Using

using System;
using System.Threading;

#endregion

namespace JenkinsWatcher
{
    public class JenkinsServer
    {
        private JenkinsApi mApi;
        public JenkinsOverview mOverview;
        private DateTime mStopTime;
        private Timer mTimer;

        public JenkinsServer(string serverUrl)
        {
            this.mApi = new JenkinsApi(serverUrl + "api/json");
            this.mOverview = this.mApi.Get<JenkinsOverview>();
        }

        public void StartConnection()
        {
            this.mStopTime = DateTime.Now.AddMinutes(10);
            this.mTimer = new Timer(WatchDogCallBack, null, 0, 5000);
        }

        public JenkinsJobOverview GetJobOverview(string url)
        {
            this.mApi = new JenkinsApi(url + "api/json");
            return this.mApi.Get<JenkinsJobOverview>();
        }

        private void WatchDogCallBack(object state)
        {
            if (DateTime.Now >= this.mStopTime)
            {
                this.mTimer.Dispose();
            }
        }
    }
}