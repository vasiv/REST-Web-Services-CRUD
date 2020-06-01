package pl.kielce.tu.crudApi.generator.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author ciepluchs
 */
public abstract class PropertiesUtil {

    private static Properties properties;

    static {
        properties = new Properties();
        try (InputStream input = PropertiesUtil.class.getClassLoader().getResourceAsStream("pl/kielce/tu/crudApi/generator/config.properties")) {
            if (input != null) {
                properties.load(input);
            } else {
                System.err.println("Cannot open properties file.");
            }
        } catch (IOException e) {

        }
    }

    private PropertiesUtil() {
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
