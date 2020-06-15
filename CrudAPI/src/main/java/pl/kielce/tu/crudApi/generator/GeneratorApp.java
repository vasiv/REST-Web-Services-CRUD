package pl.kielce.tu.crudApi.generator;


import pl.kielce.tu.crudApi.generator.database.DatabaseManager;
import pl.kielce.tu.crudApi.generator.database.MySqlManager;
import pl.kielce.tu.crudApi.generator.renderer.Renderer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

/**
 * @author ciepluchs
 */
public class GeneratorApp {

    private static final Properties config = new Properties();
    private static final String CONFIG_PATH = "/config.properties";

    public static void main(String[] args) throws SQLException {
        try {
            initializeConfig();
        } catch (IOException e) {
            System.err.println("Cannot initialize config due to: " + e);
            return;
        }
        DatabaseManager mySqlManager = MySqlManager.getJdbcManager(config);
        String tableName = config.getProperty("database.table");
        Map<String, String> namesAndTypes = mySqlManager.getColumnsMetadata(tableName);
        Renderer renderer = new Renderer(namesAndTypes, tableName);
        try {
            renderer.renderFlaskApp();
            renderer.renderIndexPage();
            renderer.renderAddPage();
        } catch (IOException e) {
            System.err.println("Cannot render project files due to: " + e);
        }
    }

    private static void initializeConfig() throws IOException {
        InputStream in = GeneratorApp.class.getResourceAsStream(CONFIG_PATH);
        config.load(in);
    }
}
