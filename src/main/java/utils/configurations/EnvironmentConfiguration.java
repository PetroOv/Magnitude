package utils.configurations;

public class EnvironmentConfiguration {
    public static final String ENVIRONMENT;
    static String GRID_URL = "http://tech-selenium1.office.fabrica.net.ua:4444/wd/hub";

    static {
        String env = System.getProperty("env");
        if (env == null)
            env = "production";
        if (env.equals("production"))
            env = "net";
        ENVIRONMENT = env;
    }

    public static void setGridUrl(String gridUrl) {
        GRID_URL = gridUrl;
    }

    public static boolean isProduction() {
        return ENVIRONMENT.equals("net");
    }

    public static boolean useSafari(){
        String safari = System.getProperty("safari");
        if(safari == null)
            return false;
        return Boolean.parseBoolean(safari);
    }
}
