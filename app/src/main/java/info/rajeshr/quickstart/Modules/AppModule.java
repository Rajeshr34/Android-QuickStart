package info.rajeshr.quickstart.Modules;


import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import info.rajeshr.quickstart.Helpers.Core.GsonStatic;
import info.rajeshr.quickstart.Modules.Scopes.BaeApplicationScope;

@Module(includes = {ContextModule.class})
public class AppModule {

    @Provides
    @BaeApplicationScope
    Gson provideGson() {
        return GsonStatic.getGson();
    }


}
