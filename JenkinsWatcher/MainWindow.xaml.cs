using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Timers;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace JenkinsWatcher
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow
    {
        private readonly JenkinsServer mServer;

        public MainWindow()
        {
            InitializeComponent();
            this.mServer = new JenkinsServer("http://192.168.0.107:8080/");
            this.JenkinsJobsView.ItemsSource = this.mServer.mOverview.jobs;
            this.JenkinsWatch.Click += JenkinsWatch_Click;
        }

        private void JenkinsWatch_Click(object sender, RoutedEventArgs e)
        {
            this.JenkinsWatch.IsEnabled = false;
            this.mServer.StartConnection();
        }
    }
}
