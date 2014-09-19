using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using Newtonsoft.Json;

namespace JenkinsWatcher
{
    public class JenkinsServer
    {
        private Timer mTimer;
        private DateTime mStopTime;
        private string mServerUrl;
        public JenkinsOverview mOverview;
        private JenkinsApi mApi;
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
                return;
            }
            // do your work.....
        }
    }
}
