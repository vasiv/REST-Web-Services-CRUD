package pl.kielce.tu.crudApi.generator.renderer;

import pl.kielce.tu.crudApi.generator.properties.PropertiesProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ciepluchs
 */
public class HeaderRendererHelper {

    private Map<String, String> replacementMap;

    public HeaderRendererHelper(Map<String, String> columnsMetadata) {
        replacementMap = buildReplacementMap(columnsMetadata);
    }

    public String[] getPlaceholders() {
        return replacementMap.keySet().toArray(new String[0]);
    }

    public String[] getTableSpecificValues() {
        return replacementMap.values().toArray(new String[0]);
    }

    private Map<String, String> buildReplacementMap(Map<String, String> columnsMetadata) {
        Map<String, String> replacementMap = new HashMap<>();
        replacementMap.put("$<tableName>", PropertiesProvider.getProperty(PropertiesProvider.DATABASE_TABLE));
        return replacementMap;
    }


}
