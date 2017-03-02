package info.rajeshr.quickstart.Helpers.Class;

import android.util.Log;

import timber.log.Timber;


public class TimberTree extends Timber.Tree {

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        if(priority == Log.ERROR) {
            //Here Push Error to Fabric or any other Logger
        }
    }

}
