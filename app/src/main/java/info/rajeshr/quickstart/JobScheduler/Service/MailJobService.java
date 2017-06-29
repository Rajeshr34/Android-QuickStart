package info.rajeshr.quickstart.JobScheduler.Service;

import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;

import info.rajeshr.quickstart.JobScheduler.Core.CoreJobService;
import info.rajeshr.quickstart.JobScheduler.Jobs.MailJob;


public class MailJobService extends CoreJobService {

    public static final String TAG = "MailJobService";

    @Override
    public boolean onStartJob(final JobParameters jobParameters) {
        setOnStartJob(jobParameters);

        /// Your Code Starts Here

        // return true if there is any asynchronous task
        // reloadError(jobParameters); when Task Leads to an Error
        // reload(jobParameters); when Task Leads to an Success

        /// Your Code Ends Here

        Log.d(TAG, "Job Started!");

        return false;
    }


    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.d(TAG, "Job cancelled!");
        return false;
    }

    @Override
    public Class getJob() {
        return MailJob.class;
    }
}