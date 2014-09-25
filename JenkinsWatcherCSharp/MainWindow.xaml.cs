using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Timers;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Controls.Primitives;
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
        /// <summary>
        /// The server
        /// </summary>
        private readonly JenkinsServer mServer;

        /// <summary>
        /// Initializes a new instance of the <see cref="MainWindow"/> class.
        /// </summary>
        public MainWindow()
        {
            InitializeComponent();
            this.mServer = new JenkinsServer("http://192.168.0.107:8080/");
            this.JenkinsJobsView.ItemsSource = this.mServer.mOverview.jobs;
            this.JenkinsWatch.Click += JenkinsWatch_Click;
        }

        /// <summary>
        /// Handles the Click event of the JenkinsWatch control.
        /// </summary>
        /// <param name="sender">The source of the event.</param>
        /// <param name="e">The <see cref="RoutedEventArgs"/> instance containing the event data.</param>
        private void JenkinsWatch_Click(object sender, RoutedEventArgs e)
        {
            this.JenkinsWatch.IsEnabled = false;
            this.mServer.StartConnection();
        }

        /// <summary>
        /// Handles the MouseDoubleClick event of the JenkinsJobsView control.
        /// </summary>
        /// <param name="sender">The source of the event.</param>
        /// <param name="e">The <see cref="MouseButtonEventArgs"/> instance containing the event data.</param>
        private void JenkinsJobsView_MouseDoubleClick(object sender, MouseButtonEventArgs e)
        {
            var selectedJob = ((FrameworkElement)e.OriginalSource).DataContext as Job;
            if (selectedJob != null)
            {
                var jobDetails = this.mServer.GetJobOverview(selectedJob.url);
            }
        }
    }
}
