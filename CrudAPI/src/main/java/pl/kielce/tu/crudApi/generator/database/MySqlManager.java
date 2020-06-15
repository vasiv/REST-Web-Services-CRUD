package pl.kielce.tu.crudApi.generator.database;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.mysql.cj.result.Field;
import pl.kielce.tu.crudApi.generator.util.JdbcUtil;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author ciepluchs
 */
public class MySqlManager implements DatabaseManager {

    private static final String USER = "database.user";
    private static final String PASSWORD = "database.password";
    private static Connection connection;

    private MySqlManager() {
    }

    public static MySqlManager getJdbcManager(Properties config) throws SQLException {
        String url = JdbcUtil.buildDatabaseUrl(config);
        String user = config.getProperty(USER);
        String password = config.getProperty(PASSWORD);
        connection = DriverManager.getConnection(url, user, password);
        return new MySqlManager();
    }


//    //TODO move to the interface
//    public static MySqlManager getJdbcManager() throws SQLException {
//        String url = "jdbc:mysql://192.168.220.197:3306/people";
//        String user = "root";
//        String password = "123";
//        connection = DriverManager.getConnection(url, user, password);
//        return new MySqlManager();
//    }

    @Override
    public Map<String, String> getColumnsMetadata(String tableName) throws SQLException{
        String query = JdbcUtil.getSelectFromStatement(tableName);
        Field[] fields = getFields(query);
        return fetchColumnsNamesAndTypes(fields);
    }

    private Map<String, String> fetchColumnsNamesAndTypes(Field[] fields) {
        Map<String, String> columnsNamesAndTypes = new HashMap<>();
        for (Field field : fields) {
            String name = field.getName();
            String type = field.getMysqlType().getClassName();
            columnsNamesAndTypes.put(name, type);
        }
        return columnsNamesAndTypes;
    }

    private Field[] getFields(String query) throws SQLException {
        try (ResultSet resultSet = connection.createStatement().executeQuery(query)) {
            ResultSetMetaData md = (ResultSetMetaData) resultSet.getMetaData();
            return md.getFields();
        }
    }
}
