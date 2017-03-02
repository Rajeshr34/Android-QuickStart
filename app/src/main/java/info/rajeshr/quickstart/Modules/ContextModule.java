package info.rajeshr.quickstart.Modules;


import android.content.Context;

import dagger.Module;
import dagger.Provides;
import info.rajeshr.quickstart.Modules.Scopes.BaeApplicationScope;

@Module
public class ContextModule {
    private final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @BaeApplicationScope
    public Context context() {
        return context;
    }
}
