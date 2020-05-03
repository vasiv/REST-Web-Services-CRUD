package pl.kielce.tu.crudApi.generator;


import pl.kielce.tu.crudApi.generator.jdbc.JdbcManager;
import pl.kielce.tu.crudApi.generator.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ciepluchs
 */
public class GeneratorApp {

    public static void main(String[] args) throws SQLException {
        String tableName = JdbcUtil.getTableName();
        JdbcManager jdbcManager = JdbcManager.getJdbcManager();
        ResultSet columnsMetadata = jdbcManager.getColumnsMetadata(tableName);
    }
}
