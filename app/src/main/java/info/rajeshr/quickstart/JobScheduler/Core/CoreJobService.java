package info.rajeshr.quickstart.JobScheduler.Core;


import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import info.rajeshr.quickstart.JobScheduler.Jobs.MailJob;

/**
 * Created by Rajesh on 29/06/2017.
 */

public abstract class CoreJobService extends JobService {

    public boolean type = false;
    public int previousID = 0;
    public int errorID = 0;

    public abstract Class getJob();

    public void setOnStartJob(JobParameters jobParameters) {
        try {
            type = jobParameters.getExtras().getBoolean(MailJob.TYPE, false);
            previousID = jobParameters.getExtras().getInt(MailJob.PREVIOUS_ID, 0);
            errorID = jobParameters.getExtras().getInt(MailJob.ERROR_ID, 0);
        } catch (Exception ignored) {

        }
    }

    /**
     * Call This after callback completed
     *
     * @param jobParameters
     */
    public void reloadError(JobParameters jobParameters) {
        reload(jobParameters, false);
    }


    /**
     * Call This after callback completed
     *
     * @param jobParameters
     */

    public void reload(JobParameters jobParameters) {
        reload(jobParameters, true);
    }

    public void reload(JobParameters jobParameters, boolean success) {
        jobFinished(jobParameters, false);
        String className = getJob().getName();
        if (className.equals(MailJob.class.getName())) {
            new MailJob(getApplicationContext(), previousID, errorID, false, success).start();
        }
    }

}
