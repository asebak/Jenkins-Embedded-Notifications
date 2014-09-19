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
        private string mServerUrl;
        private DateTime mStopTime;
        private Timer mTimer;

        public JenkinsServer(string serverUrl)
        {
            this.mServerUrl = serverUrl;
            this.mApi = new JenkinsApi(serverUrl + "api/json");
            this.mOverview = this.mApi.Get<JenkinsOverview>();
        }

        public void StartConnection()
        {
            this.mStopTime = DateTime.Now.AddMinutes(10);
            this.mTimer = new Timer(WatchDogCallBack, null, 0, 5000);
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