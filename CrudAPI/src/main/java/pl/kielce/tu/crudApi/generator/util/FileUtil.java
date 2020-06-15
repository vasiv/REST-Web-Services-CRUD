package pl.kielce.tu.crudApi.generator.util;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * Responsible for File operations like reading, creating etc.
 *
 * @author ciepluchs
 */
public abstract class FileUtil {

    private FileUtil() {
    }

    /**
     * Creates file with given file name under given directory. If folders doesn't exist, they will be created also.
     *
     * @param directory - path where file should be created
     * @param fileName  - name for generated file
     * @return created File object
     * @throws IOException
     */
    public static File createFileWithFullDirectoryPath(String directory, String fileName) throws IOException {
        Files.createDirectories(Paths.get(directory));
        File file = new File(directory + File.separator + fileName);
        return file;
    }

    /**
     * Opens file by given path and returns content as String.
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String readFileToString(String filePath) throws IOException {
        InputStream fileStream = FileUtil.class.getResourceAsStream(filePath);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(fileStream, Charset.defaultCharset()))) {
            return br.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }
}
