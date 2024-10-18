package regex.Services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderServiceTest {

    private FileReaderService fileReaderService;
    private Path tempFile;

    @BeforeEach
    void setUp() throws IOException {
        fileReaderService = new FileReaderService();
        tempFile = Files.createTempFile("testFile", ".txt");
        Files.writeString(tempFile, "Test content");
    }

    @Test
    void testReadFileSuccess() throws IOException {
        String content = fileReaderService.readFile(tempFile.toString());
        assertEquals("Test content", content);
    }

    @Test
    void testReadFileThrowsExceptionForNonExistentFile() {
        assertThrows(IOException.class, () -> fileReaderService.readFile("non_existent_file.txt"));
    }
}
