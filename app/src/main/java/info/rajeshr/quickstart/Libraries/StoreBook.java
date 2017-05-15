package info.rajeshr.quickstart.Libraries;


import info.rajeshr.quickstart.Models.BaseConfigModel;

public class StoreBook {

    private static final String BASE_CONFIG_MODEL = "BASE_CONFIG_MODEL";
    private static BaseConfigModel configModel;

    public static BaseConfigModel getConfigBook() {
        if (configModel == null)
            configModel = PaperDB.read(BASE_CONFIG_MODEL, new BaseConfigModel());
        return configModel;
    }

    public static void saveConfigBook() {
        PaperDB.write(BASE_CONFIG_MODEL, getConfigBook());
    }
}
