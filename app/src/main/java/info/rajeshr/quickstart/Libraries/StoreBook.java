package info.rajeshr.quickstart.Libraries;


import info.rajeshr.quickstart.Models.BaseConfigModel;
import io.paperdb.Paper;

public class StoreBook {

    private static final String BASE_CONFIG_MODEL = "BASE_CONFIG_MODEL";
    private static BaseConfigModel configModel;

    public static BaseConfigModel getConfigBook() {
        if (configModel == null)
            configModel = Paper.book().read(BASE_CONFIG_MODEL, new BaseConfigModel());
        return configModel;
    }

    public static void saveConfigBook() {
        Paper.book().write(BASE_CONFIG_MODEL, getConfigBook());
    }
}
