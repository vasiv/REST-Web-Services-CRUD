package pl.kielce.tu.crudApi.generator.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ciepluchs
 */
public abstract class JdbcUtil {

    public static final String SHOW_COLUMNS_STATEMENT = "SHOW COLUMNS FROM ";

    private static final String JDBC_PREFIX = "jdbc:";
    private static final String COLON = ":";
    private static final String SLASH = "/";
    private static final String DATABASE = "database";
    private static final String TYPE = "type";
    private static final String LOCATION = "location";
    private static final String PORT = "port";
    private static final String DOT = ".";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String SCHEMA = "schema";
    private static final String TABLE = "table";

    private static final List<String> propertiesKeys = Arrays.asList(TYPE, LOCATION, PORT, USER, PASSWORD,
            SCHEMA, TABLE);
    private static final Map<String, String> databaseProperties;

    static {
        databaseProperties = getDatabaseProperties();
    }

    private JdbcUtil() {
    }


    public static String getDatabaseUrl() {
        StringBuilder sb = new StringBuilder();
        sb.append(JDBC_PREFIX);
        sb.append(databaseProperties.get(TYPE));
        sb.append(COLON).append(SLASH).append(SLASH);
        sb.append(databaseProperties.get(LOCATION));
        sb.append(COLON);
        sb.append(databaseProperties.get(PORT));
        sb.append(SLASH);
        sb.append(databaseProperties.get(SCHEMA));
        return sb.toString();
    }

    public static String getUser() {
        return databaseProperties.get(USER);
    }

    public static String getPassword() {
        return databaseProperties.get(USER);
    }

    public static String getTableName(){return databaseProperties.get(TABLE);}

    private static Map<String, String> getDatabaseProperties() {
        Map<String, String> databaseProperties = new HashMap<>();
        propertiesKeys.forEach(key -> {
            databaseProperties.put(key, PropertiesUtil.getProperty(DATABASE + DOT + key));
        });
        return databaseProperties;
    }
}
