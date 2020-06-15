package pl.kielce.tu.crudApi.generator.renderer;

import org.apache.commons.lang3.StringUtils;
import pl.kielce.tu.crudApi.generator.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;

/**
 * @author ciepluchs
 */
public class Renderer {

    private static final String INDEX_PAGE_PATH = "flask_application/templates/index.html";
    private static final String ADD_FORM_PATH = "flask_application/templates/add.html";
    private static final String FLASK_APP_PATH = "/flask_application/__init__.py";
    private static final String OUTPUT = "D:/to_crud/";
    private static final String FLASK_APP_TEMPLATE_PATH = "__init__.py";
    private static final String FLASK_APP = "__init__.py";
    private static final String INDEX_PAGE = "index.html";
    private static final String ADD_PAGE = "add.html";

    private Map<String, String> columnsMetadata;
    private String tableName;
    private String outputDirectiory;

    public Renderer(Map<String, String> columnsMetadata, String tableName) {
        this.columnsMetadata = columnsMetadata;
        this.tableName = tableName;
        outputDirectiory = System.getProperty("user.dir");
    }

    //TODO make generic method handling all files

    public void renderFlaskApp() throws IOException {
        FlaskAppRendererHelper flaskAppRendererHelper = new FlaskAppRendererHelper(columnsMetadata, tableName);
        String flaskAppFileTemplate = FileUtil.readFileToString(FLASK_APP_PATH);
        String[] placeholders = flaskAppRendererHelper.getPlaceholders();
        String[] specificValues = flaskAppRendererHelper.getTableSpecificValues();
        String updatedFlaskAppFileContent =
                StringUtils.replaceEach(flaskAppFileTemplate, placeholders, specificValues);
        File flaskAppFile = FileUtil.createFileWithFullDirectoryPath(OUTPUT, ADD_PAGE);
        try (PrintStream out = new PrintStream(flaskAppFile)) {
            out.print(updatedFlaskAppFileContent);
        }
    }

    public void renderIndexPage() throws IOException {
        IndexPageRendererHelper indexPageRendererHelper = new IndexPageRendererHelper(columnsMetadata, tableName);
        String indexPageTemplate = FileUtil.readFileToString(INDEX_PAGE_PATH);
        String[] placeholders = indexPageRendererHelper.getPlaceholders();
        String[] specificValues = indexPageRendererHelper.getTableSpecificValues();
        String updatedIndexPageFileContent = StringUtils.replaceEach(indexPageTemplate, placeholders, specificValues);
        File indexPageFile = FileUtil.createFileWithFullDirectoryPath(OUTPUT, ADD_PAGE);
        try (PrintStream out = new PrintStream(indexPageFile)) {
            out.print(updatedIndexPageFileContent);
        }
    }

    public void renderAddPage() throws IOException {
        return;
    }

}
