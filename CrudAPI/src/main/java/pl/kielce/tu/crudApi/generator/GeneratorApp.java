package pl.kielce.tu.crudApi.generator;


import pl.kielce.tu.crudApi.generator.database.DatabaseManager;
import pl.kielce.tu.crudApi.generator.database.MySqlManager;
import pl.kielce.tu.crudApi.generator.properties.PropertiesProvider;
import pl.kielce.tu.crudApi.generator.renderer.Renderer;
import pl.kielce.tu.crudApi.generator.util.FileUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author ciepluchs
 */
public class GeneratorApp {

    public static void main(String[] args) throws SQLException {

        DatabaseManager mySqlManager = MySqlManager.getJdbcManager();
        String tableName = PropertiesProvider.getProperty(PropertiesProvider.DATABASE_TABLE);
        Map<String, String> namesAndTypes = mySqlManager.getColumnsMetadata(tableName);
        Renderer renderer = new Renderer(namesAndTypes);

        try {
            renderer.renderFlaskApp();
            renderer.renderIndexPage();
            renderer.renderHeader();
            FileUtil.copyFromJar("/flask_application/static", Paths.get(PropertiesProvider.getProperty(PropertiesProvider.OUTPUT_DIRECTORY) + "static/"));
        } catch (IOException e) {
            System.err.println("Cannot render project files due to: " + e);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

}
