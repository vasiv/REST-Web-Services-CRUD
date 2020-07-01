package pl.kielce.tu.crudApi.generator.renderer;

import pl.kielce.tu.crudApi.generator.properties.PropertiesProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ciepluchs
 */
public class IndexPageRendererHelper {

    private Map<String, String> replacementMap;

    public IndexPageRendererHelper(Map<String, String> columnsMetadata) {
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
        List<String> columnNames = new ArrayList<>(columnsMetadata.keySet());
        replacementMap.put("$<tableName>", PropertiesProvider.getProperty(PropertiesProvider.DATABASE_TABLE));
        replacementMap.put("$<columnHeaders>", buildColumnHeaders(columnNames));
        replacementMap.put("$<rows>", buildRows(columnNames));
        replacementMap.put("$<updateFormInput>", buildUpdateFormInput(columnNames));
        return replacementMap;
    }

    private String buildUpdateFormInput(List<String> columnNames) {
        StringBuilder sb = new StringBuilder();
        boolean isFirstInput = true;
        for (String columnName : columnNames) {
            if ("id".equals(columnName)) continue;
            sb.append("                                    <div class=\"form-group\">\n")
                    .append("                                        <label>")
                    .append(columnName)
                    .append(":</label>\n")
                    .append("                                        <input type=\"text\" class=\"form-control\" name=\"")
                    .append(columnName)
                    .append("\" value=\"{{row.")
                    .append(columnNames.indexOf(columnName))
                    .append("}}\">\n");
            if (isFirstInput) {
                sb.append("                                        <input type=\"hidden\" name=\"id\" value=\"{{row.0}}\">\n");
                isFirstInput = false;
            }
            sb.append("                                    </div>\n");
        }
        return sb.toString();
    }

    private String buildRows(List<String> columnNames) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < columnNames.size(); i++) {
            sb.append("                    <td>{{row.")
                    .append(i)
                    .append("}}</td>\n");
        }
        return sb.toString();
    }

    private String buildColumnHeaders(List<String> columnNames) {
        StringBuilder sb = new StringBuilder();
        sb.append("                <tr>\n");
        for (String columnName : columnNames) {
            sb.append("                    <th>")
                    .append(columnName)
                    .append("</th>\n");
        }
        sb.append("                </tr>\n");
        return sb.toString();
    }
}
