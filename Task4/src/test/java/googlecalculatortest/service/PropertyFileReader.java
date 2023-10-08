package googlecalculatortest.service;

import java.util.ResourceBundle;

public class PropertyFileReader {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("environment"));

    public static String getEnvironment(String key) {
        return resourceBundle.getString(key);
    }
}
