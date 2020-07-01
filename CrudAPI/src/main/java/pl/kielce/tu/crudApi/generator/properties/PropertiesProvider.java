package pl.kielce.tu.crudApi.generator.properties;

import pl.kielce.tu.crudApi.generator.GeneratorApp;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author ciepluchs
 */
public class PropertiesProvider {

    public static final String DATABASE_TABLE = "database.table";
    public static final String OUTPUT_DIRECTORY = "output.directory";
    public static final String DATABASE_HOST = "database.location";
    public static final String DATABASE_USER = "database.user";
    public static final String DATABASE_PASSWORD = "database.password";
    public static final String DATABASE_SCHEMA = "database.schema";

    private static final String CONFIG_PATH = "/config.properties";
    private static final Properties properties = new Properties();

    static {
        InputStream in = GeneratorApp.class.getResourceAsStream(CONFIG_PATH);
        try {
            properties.load(in);
        } catch (IOException e) {
            System.err.println("Cannot initialize properties due to: " + e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
