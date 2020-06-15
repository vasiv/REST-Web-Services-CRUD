package pl.kielce.tu.crudApi.generator.database;

import java.sql.SQLException;
import java.util.Map;

public interface DatabaseManager {

    /**
     * Retrieves column's names and types of given table.
     *
     * @param tableName - name of table which columns information should be retrieved
     * @return Map with name and type (Java type class name) of each column
     */
    Map<String, String> getColumnsMetadata(String tableName) throws SQLException;
}
