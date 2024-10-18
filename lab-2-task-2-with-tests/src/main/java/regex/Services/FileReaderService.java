package regex.Services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReaderService {
    public String readFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}

