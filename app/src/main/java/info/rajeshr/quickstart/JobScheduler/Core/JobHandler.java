package info.rajeshr.quickstart.JobScheduler.Core;

import android.content.Context;
import android.os.Bundle;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;

import info.rajeshr.quickstart.JobScheduler.Model.JobHandlerStartEndModel;


/**
 * Created by Rajesh on 29/06/2017.
 */

public abstract class JobHandler {

    protected static String PREVIOUS_ID = "PREVIOUS_ID";
    protected static String ERROR_ID = "ERROR_ID";
    protected static String TYPE = "TYPE";

    private Context context;
    private int lastInt;
    private int errorID;
    private boolean onStart;
    private boolean success;
    private FirebaseJobDispatcher dispatcher;

    public abstract String getId();

    public abstract Job runJob();

    public JobHandler(Context context, boolean onStart) {
        this.context = context;
        this.onStart = onStart;
    }

    public JobHandler(Context context, int lastInt, int errorID, boolean onStart, boolean success) {
        this.context = context;
        this.lastInt = lastInt;
        this.errorID = errorID;
        this.onStart = onStart;
        this.success = success;
    }

    protected FirebaseJobDispatcher getDispatcher() {
        if (dispatcher == null)
            dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(context));
        return dispatcher;
    }

    protected JobHandlerStartEndModel getTriggerData() {
        return getTriggerData(onStart, lastInt, errorID);
    }

    private JobHandlerStartEndModel getTriggerData(Boolean onStart, int previousID, int errorID) {
        int start = 0;
        int end = 0;
        int nextID = previousID + 1;
        int nextErrorID = 0;

        if (!success) {
            if (errorID <= 2) {
                previousID = 0;
                nextErrorID = errorID + 1;
            }
        }

        switch (previousID) {
            case 0:
            case 1:
                start = setMinutes(2);
                end = setMinutes(15);
                //every firteen minutes from 2 mins to 15 mins
                break;
            case 2:
                start = setHours(1);
                end = setHours(4);
                //run thrice a day from 1 hr to 4 hr
                break;
            case 3:
                start = setHours(1);
                end = setHours(7);
                //run twice a day
                break;
            default:
                //once a day
                nextID = 4;
                start = setHours(1);
                end = setHours(24);
                break;
        }

        if (onStart) {
            nextID = 1;
            start = 0;
            end = 0;
        }
        return new JobHandlerStartEndModel(start, end, nextID, nextErrorID);
    }

    public Bundle getBundle() {
        Bundle extras = new Bundle();
        extras.putBoolean(TYPE, onStart);
        extras.putInt(PREVIOUS_ID, getTriggerData().getNextId());
        extras.putInt(ERROR_ID, getTriggerData().getNextErrorID());
        return extras;
    }

    private int setHours(int i) {
        return i * 60 * 60;

    }

    private int setMinutes(int i) {
        return i * 60;
    }

    public void start() {
        getDispatcher().mustSchedule(runJob());
    }

    /// ----------------- STATIC ---------------------------

    private static FirebaseJobDispatcher getDispatcherStatic(Context context) {
        return new FirebaseJobDispatcher(new GooglePlayDriver(context));
    }

    public static void cancelAll(Context context) {
        getDispatcherStatic(context).cancelAll();
    }

    public static void cancel(Context context, String jobTag) {
        getDispatcherStatic(context).cancel(jobTag);
    }


}
