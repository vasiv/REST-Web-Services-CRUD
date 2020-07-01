package pl.kielce.tu.crudApi.generator.renderer;

import org.apache.commons.lang3.StringUtils;
import pl.kielce.tu.crudApi.generator.properties.PropertiesProvider;
import pl.kielce.tu.crudApi.generator.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;

/**
 * @author ciepluchs
 */
public class Renderer {

    private static final String INDEX_PAGE_PATH = "/flask_application/templates/index.html";
    private static final String HEADER_PAGE_PATH = "/flask_application/templates/header.html";
    private static final String FLASK_APP_PATH = "/flask_application/__init__.py";
    private static final String FLASK_APP = "__init__.py";
    private static final String INDEX_PAGE = "index.html";
    private static final String HEADER = "header.html";
    private static final String TEMPLATES_DIRECTORY = "templates/";

    private Map<String, String> columnsMetadata;
    private String outputDirectory;

    public Renderer(Map<String, String> columnsMetadata) {
        this.columnsMetadata = columnsMetadata;
        this.outputDirectory = PropertiesProvider.getProperty(PropertiesProvider.OUTPUT_DIRECTORY);
    }

    public void renderFlaskApp() throws IOException {
        FlaskAppRendererHelper flaskAppRendererHelper = new FlaskAppRendererHelper(columnsMetadata);
        String flaskAppFileTemplate = FileUtil.readFileToString(FLASK_APP_PATH);
        String[] placeholders = flaskAppRendererHelper.getPlaceholders();
        String[] specificValues = flaskAppRendererHelper.getSpecificValues();
        String updatedFlaskAppFileContent =
                StringUtils.replaceEach(flaskAppFileTemplate, placeholders, specificValues);
        File flaskAppFile = FileUtil.createFileWithFullDirectoryPath(outputDirectory, FLASK_APP);
        try (PrintStream out = new PrintStream(flaskAppFile)) {
            out.print(updatedFlaskAppFileContent);
        }
    }

    public void renderIndexPage() throws IOException {
        IndexPageRendererHelper indexPageRendererHelper = new IndexPageRendererHelper(columnsMetadata);
        String indexPageTemplate = FileUtil.readFileToString(INDEX_PAGE_PATH);
        String[] placeholders = indexPageRendererHelper.getPlaceholders();
        String[] specificValues = indexPageRendererHelper.getTableSpecificValues();
        String updatedIndexPageFileContent = StringUtils.replaceEach(indexPageTemplate, placeholders, specificValues);
        File indexPageFile =
                FileUtil.createFileWithFullDirectoryPath(outputDirectory + TEMPLATES_DIRECTORY, INDEX_PAGE);
        try (PrintStream out = new PrintStream(indexPageFile)) {
            out.print(updatedIndexPageFileContent);
        }
    }

    public void renderHeader() throws IOException {
        HeaderRendererHelper headerRendererHelper = new HeaderRendererHelper(columnsMetadata);
        String headerTemplate = FileUtil.readFileToString(HEADER_PAGE_PATH);
        String[] placeholders = headerRendererHelper.getPlaceholders();
        String[] specificValues = headerRendererHelper.getTableSpecificValues();
        String updatedIndexPageFileContent = StringUtils.replaceEach(headerTemplate, placeholders, specificValues);
        File indexPageFile =
                FileUtil.createFileWithFullDirectoryPath(outputDirectory + TEMPLATES_DIRECTORY, HEADER);
        try (PrintStream out = new PrintStream(indexPageFile)) {
            out.print(updatedIndexPageFileContent);
        }
    }
}
