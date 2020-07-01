package pl.kielce.tu.crudApi.generator.util;

import pl.kielce.tu.crudApi.generator.properties.PropertiesProvider;

/**
 * @author ciepluchs
 */
public abstract class JdbcUtil {

    private static final String SELECT = "SELECT * FROM ";
    private static final String JDBC_PREFIX = "jdbc:";
    private static final String COLON = ":";
    private static final String SEMICOLON = ";";
    private static final String SLASH = "/";
//    private static final String DATABASE = "database";
    private static final String TYPE = "database.type";
    private static final String LOCATION = "database.location";
    private static final String PORT = "database.port";
//    private static final String DOT = ".";
    private static final String SCHEMA = "database.schema";
//    private static final String TABLE = "database.table";

    private JdbcUtil() {
    }

    public static String getSelectFromStatement(String tableName) {
        return SELECT + tableName + SEMICOLON;
    }

    public static String buildDatabaseUrl() {
        StringBuilder sb = new StringBuilder();
        sb.append(JDBC_PREFIX);
        sb.append(PropertiesProvider.getProperty(TYPE));
        sb.append(COLON).append(SLASH).append(SLASH);
        sb.append(PropertiesProvider.getProperty(LOCATION));
        sb.append(COLON);
        sb.append(PropertiesProvider.getProperty(PORT));
        sb.append(SLASH);
        sb.append(PropertiesProvider.getProperty(SCHEMA));
        return sb.toString();
    }
}
