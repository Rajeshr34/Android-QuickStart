package info.rajeshr.quickstart.JobScheduler.JobsHandler;

import android.content.Context;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

import info.rajeshr.quickstart.JobScheduler.Core.JobHandler;
import info.rajeshr.quickstart.JobScheduler.Service.MailJobService;


/**
 * Created by Rajesh on 28/06/2017.
 */

public class MailJob extends JobHandler {


    public MailJob(Context context, boolean onStart) {
        super(context, onStart);
    }

    public MailJob(Context context, int lastInt, int errorID, boolean onStart, boolean success) {
        super(context, lastInt, errorID, onStart, success);
    }

    @Override
    public String getId() {
        return "MAIL_SCAN";
    }

    @Override
    public Job runJob() {
        return getDispatcher().newJobBuilder()
                .setTag(getId())
                .setLifetime(Lifetime.FOREVER)
                .setReplaceCurrent(true)
                .setService(MailJobService.class)
                .setTrigger(Trigger.executionWindow(getTriggerData().getStart(), getTriggerData().getEnd()))
                .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setExtras(getBundle())
                .build();
    }
}
