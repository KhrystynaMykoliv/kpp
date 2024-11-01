package dormitories.Services.Serializers.Json;

import dormitories.Models.Student;
import dormitories.Seeds.Students;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonSerializeServiceTest {
    private JsonSerializeService jsonSerializeService;
    private File tempFile;

    @BeforeEach
    void setUp() throws IOException {
        jsonSerializeService = new JsonSerializeService();
        tempFile = File.createTempFile("students", ".json");
    }

    @AfterEach
    void tearDown() {
        tempFile.delete();
    }

    @Test
    void testSerialize() throws IOException {
        List<Student> students = Students.getStudents();
        jsonSerializeService.serialize(students, tempFile.getPath());

        assertTrue(tempFile.exists());

        assertTrue(tempFile.length() > 0);
    }
}
