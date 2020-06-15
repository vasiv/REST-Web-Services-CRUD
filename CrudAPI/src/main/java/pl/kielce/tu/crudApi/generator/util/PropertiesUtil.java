package pl.kielce.tu.crudApi.generator.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author ciepluchs
 */
public class PropertiesUtil {

//    private static final Properties properties;

    static {

    }

    public String getProperty(String key) {
        Properties properties = new Properties();
        try (InputStream input = this.getClass().getResourceAsStream("config.properties")) {
            if (input != null) {
                properties.load(input);
            } else {
                System.err.println("Cannot open properties file.");
            }
        } catch (IOException e) {

        }
        return properties.getProperty(key);
    }
}
