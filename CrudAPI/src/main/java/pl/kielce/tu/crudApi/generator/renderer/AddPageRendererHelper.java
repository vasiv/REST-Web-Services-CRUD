package pl.kielce.tu.crudApi.generator.renderer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ciepluchs
 */
public class AddPageRendererHelper {

    public String renderAddForm() {
        Map<String, String> data = new HashMap<>();
        data.put("$<insertFormFields>", "<div class=\"form-group\">\n" +
                "            <label for=\"name\">Student Name</label>\n" +
                "            <input type=\"text\" class=\"form-control\" name=\"name\" id=\"name\" required=\"1\">\n" +
                "        </div>");

        return null;
    }
}
