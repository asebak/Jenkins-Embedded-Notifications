using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;

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
