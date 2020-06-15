package pl.kielce.tu.crudApi.generator.renderer;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ciepluchs
 */
public class FlaskAppRendererHelper {

    private Map<String, String> replacementMap;

    public FlaskAppRendererHelper(Map<String, String> columnsMetadata, String tableName) {
        replacementMap = buildReplacementMap(columnsMetadata, tableName);
    }

    public String[] getPlaceholders() {
        return replacementMap.keySet().toArray(new String[0]);
    }

    public String[] getTableSpecificValues() {
        return replacementMap.values().toArray(new String[0]);
    }

    private Map<String, String> buildReplacementMap(Map<String, String> columnsMetadata, String tableName) {
        Map<String, String> replacementMap = new HashMap<>();
        replacementMap.put("$<tableName>", tableName);
        replacementMap.put("$<insertFormFieldsPython>", buildInsertFormFieldsPython(columnsMetadata));
        replacementMap.put("$<insertStatement>", buildInsertStatement(columnsMetadata));
        replacementMap.put("$<updateFormFields>", buildUpdateFormFields(columnsMetadata));
        replacementMap.put("$<updateStatement>", buildUpdateStatement(columnsMetadata, tableName));
        return replacementMap;
    }

    private String buildUpdateFormFields(Map<String, String> columnsMetadata) {
        StringBuilder sb = new StringBuilder();
        sb.append("        id_data = request.form['id']\n");
        for (String columnName : columnsMetadata.keySet()) {
            sb.append("        ")
                    .append(columnName)
                    .append(" = request.form['")
                    .append(columnName)
                    .append("']\n");
        }
        return sb.toString();
    }

    private String buildInsertStatement(Map<String, String> columnsMetadata) {
        StringBuilder sb = new StringBuilder();
        sb.append("cur.execute(\"INSERT INTO students ");
        sb.append(getColumnsNamesBetweenParentheses(columnsMetadata));
        sb.append(" VALUES (");
        String separator = "";
        for (int i = 0; i < columnsMetadata.size(); i++) {
            sb.append(separator)
                    .append("%s");
            separator = ", ";
        }
        sb.append(")\", ");
        sb.append(getColumnsNamesBetweenParentheses(columnsMetadata));
        sb.append(")");
        return sb.toString();
    }

    private String getColumnsNamesBetweenParentheses(Map<String, String> columnsMetadata) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        String separator = "";
        for (String columnName : columnsMetadata.keySet()) {
            sb.append(separator).append(columnName);
            separator = ", ";
        }
        sb.append(")");
        return sb.toString();
    }

    private String buildInsertFormFieldsPython(Map<String, String> columnsMetadata) {
        StringBuilder sb = new StringBuilder();
        for (String columnName : columnsMetadata.keySet()) {
            sb.append("        ")
                    .append(columnName)
                    .append(" =  request.form['")
                    .append(columnName)
                    .append("']\n");
        }
        return sb.toString();
    }

    private String buildUpdateStatement(Map<String, String> columnsMetadata, String tableName) {
        StringBuilder sb = new StringBuilder();
        sb.append("         cur.execute(\"UPDATE ")
                .append(tableName)
                .append(" SET ");
        String separator = StringUtils.EMPTY;
        for (String columnName : columnsMetadata.keySet()) {
            sb.append(separator)
                    .append(columnName)
                    .append("=%s");
            separator = ", ";
        }
        sb.append(" WHERE id=%s\", ")
                .append(getColumnsNamesBetweenParentheses(columnsMetadata))
                .append(")\n");
        return sb.toString();
    }
}
