package info.rajeshr.quickstart.Libraries;


import info.rajeshr.quickstart.Models.BaseConfigModel;
import info.rajeshr.quickstart.Models.TokenModel;

public class StoreBook {

    private static final String BASE_CONFIG_MODEL = "BASE_CONFIG_MODEL";
    private static final String TOKEN_MODEL = "TOKEN_MODEL";

    private static BaseConfigModel configModel;
    private static TokenModel tokenModel;

    public static BaseConfigModel getConfigBook() {
        if (configModel == null)
            configModel = PaperDB.read(BASE_CONFIG_MODEL, new BaseConfigModel());
        return configModel;
    }

    public static void saveConfigBook() {
        PaperDB.write(BASE_CONFIG_MODEL, getConfigBook());
    }

    public static TokenModel getTokenBook() {
        if (tokenModel == null)
            tokenModel = PaperDB.read(TOKEN_MODEL, new TokenModel());
        return tokenModel;
    }

    public static void saveTokenBook() {
        PaperDB.write(TOKEN_MODEL, getTokenBook());
    }
}
