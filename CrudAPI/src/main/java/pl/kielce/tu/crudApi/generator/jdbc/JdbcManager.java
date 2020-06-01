package pl.kielce.tu.crudApi.generator.jdbc;

import pl.kielce.tu.crudApi.generator.util.JdbcUtil;

import java.sql.*;

/**
 * @author ciepluchs
 */
public class JdbcManager {

    private static Connection connection;

    private JdbcManager() {
    }

    public static JdbcManager getJdbcManager() throws SQLException {
        String url = JdbcUtil.getDatabaseUrl();
        String user = JdbcUtil.getUser();
        String password = JdbcUtil.getPassword();
        connection = DriverManager.getConnection(url, user, password);
        return new JdbcManager();
    }

    public ResultSet getColumnsMetadata(String tableName) throws SQLException {
        String query = JdbcUtil.SHOW_COLUMNS_STATEMENT + tableName;
        try (Statement statement = connection.createStatement()){
            return statement.executeQuery(query);
        }
    }
}
