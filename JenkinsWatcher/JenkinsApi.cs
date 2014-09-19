#region Using

using System.Net.Http;
using Newtonsoft.Json;

#endregion

namespace JenkinsWatcher
{
    public class JenkinsApi
    {
        private readonly string mUrl;

        public JenkinsApi(string uri)
        {
            this.mUrl = uri;
        }

        public T Get<T>()
        {
            using (var httpClient = new HttpClient())
            {
                var response = httpClient.GetAsync(this.mUrl).Result;
                return JsonConvert.DeserializeObject<T>(response.Content.ReadAsStringAsync().Result);
            }
        }
    }
}