package pl.kielce.tu.crudApi.generator.flask;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ciepluchs
 */
public class Renderer {

    private static final String INDEX_PAGE_PATH = "CrudAPI/flask_application/templates/index.html";
    private static final String ADD_FORM_PATH = "CrudAPI/flask_application/templates/add.html";
    private static final String FLASP_APP_PATH = "CrudAPI/flask_application/__init__.py";

    public static String renderIndexPage(){
        String indexPage = readFileToString(INDEX_PAGE_PATH);
        Map<String, String> data = new HashMap<>();
        data.put("$<tableName>", "Students");
        data.put("$<columnHeaders>", "                <tr>\n" +
                "                    <th>Serial</th>\n" +
                "                    <th>Name</th>\n" +
                "                    <th>Email</th>\n" +
                "                    <th>Phone</th>\n" +
                "                    <th>Action</th>\n" +
                "                </tr>");
        data.put("$<rows>", "                <tr>\n" +
                "                    <td>{{row.0}}</td>\n" +
                "                    <td>{{row.1}}</td>\n" +
                "                    <td>{{row.2}}</td>\n" +
                "                    <td>{{row.3}}</td>\n" +
                "                    <td>\n" +
                "                        <a href=\"/update/{{row.0}}\" class=\"btn btn-warning btn-xs\" data-toggle=\"modal\"\n" +
                "                           data-target=\"#modaledit{{row.0}}\">Edit</a>\n" +
                "                        <a href=\"/delete/{{ row.0 }}\" class=\"btn btn-danger btn-xs\"\n" +
                "                           onclick=\"return confirm('Are You Sure For Delete?')\">Delete</a>\n" +
                "                    </td>\n" +
                "                </tr>");
//        ID handling
        data.put("$<updateFormInput>", "                                            <div class=\"form-group\">\n" +
                "                                                <label>Name:</label>\n" +
                "                                                <input type=\"hidden\" name=\"id\" value=\"{{row.0}}\">\n" +
                "                                                <input type=\"text\" class=\"form-control\" name=\"name\" value=\"{{row.1}}\">\n" +
                "                                            </div>");
        return "";
    }

    public static String renderFlask(){
        String filePath = "c:/temp/data.txt";

        Map<String, String> data = new HashMap<>();
        data.put("$<insertFormFieldsPython>", "name = request.form['name']\n" +
                "        email = request.form['email']\n" +
                "        phone = request.form['phone']\n");

        data.put("$<insertStatement>", "cur.execute(\"INSERT INTO students (name, email, phone) VALUES (%s, %s, %s)\", (name, email, phone))");
        data.put("$<deleteStatement>", "cur.execute(\"DELETE FROM students WHERE id=%s\", (id_data,))");
        data.put("$<updateFormFields>", "        id_data = request.form['id']\n" +
                "        name = request.form['name']\n" +
                "        email = request.form['email']\n" +
                "        phone = request.form['phone']");
        data.put("$<updateStatement>", "        cur.execute(\"\"\"\n" +
                "               UPDATE students\n" +
                "               SET name=%s, email=%s, phone=%s\n" +
                "               WHERE id=%s\n" +
                "            \"\"\", (name, email, phone, id_data))");
        return null;
    }

    public static String renderAddForm(){
        Map<String, String> data = new HashMap<>();
        data.put("$<insertFormFields>", "<div class=\"form-group\">\n" +
                "            <label for=\"name\">Student Name</label>\n" +
                "            <input type=\"text\" class=\"form-control\" name=\"name\" id=\"name\" required=\"1\">\n" +
                "        </div>");

        return null;
    }

    private static String readFileToString(String filePath) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            System.err.println("Exception during file reading " + e);
        }
        return content;
    }

}
