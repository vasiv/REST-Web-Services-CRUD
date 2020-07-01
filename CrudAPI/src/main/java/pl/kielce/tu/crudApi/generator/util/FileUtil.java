package pl.kielce.tu.crudApi.generator.util;

import pl.kielce.tu.crudApi.generator.GeneratorApp;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
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


    public static void copyFromJar(String source, final Path target) throws URISyntaxException, IOException {
        URI resource = FileUtil.class.getResource("").toURI();
        FileSystem fileSystem = FileSystems.newFileSystem(
                resource,
                Collections.<String, String>emptyMap()
        );


        final Path jarPath = fileSystem.getPath(source);

        Files.walkFileTree(jarPath, new SimpleFileVisitor<Path>() {

            private Path currentTarget;

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                currentTarget = target.resolve(jarPath.relativize(dir).toString());
                Files.createDirectories(currentTarget);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.copy(file, target.resolve(jarPath.relativize(file).toString()), StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }

        });
    }
}
