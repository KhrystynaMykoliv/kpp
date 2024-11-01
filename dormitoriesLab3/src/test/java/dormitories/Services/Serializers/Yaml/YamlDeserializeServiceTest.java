package dormitories.Services.Serializers.Yaml;

import dormitories.Models.Student;
import dormitories.Seeds.Students;
import dormitories.Services.Serializers.Yaml.YamlDeserializeService;
import dormitories.Services.Serializers.Yaml.YamlSerializeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class YamlDeserializeServiceTest {
    private YamlDeserializeService yamlDeserializeService;
    private YamlSerializeService yamlSerializeService;
    private File tempFile;

    @BeforeEach
    void setUp() throws IOException {
        yamlDeserializeService = new YamlDeserializeService();
        yamlSerializeService = new YamlSerializeService();
        tempFile = File.createTempFile("students", ".yaml");
    }

    @AfterEach
    void tearDown() {
        tempFile.delete();
    }

    @Test
    void testDeserialize() throws IOException {
        List<Student> originalStudents = Students.getStudents();

        yamlSerializeService.serialize(originalStudents, tempFile.getPath());

        List<Student> deserializedStudents = yamlDeserializeService.deserialize(tempFile.getPath());

        assertNotNull(deserializedStudents);
        assertEquals(originalStudents.size(), deserializedStudents.size());
        for (int i = 0; i < originalStudents.size(); i++) {
            assertEquals(originalStudents.get(i).getFirstName(), deserializedStudents.get(i).getFirstName());
            assertEquals(originalStudents.get(i).getLastName(), deserializedStudents.get(i).getLastName());
            assertEquals(originalStudents.get(i).getRoom().getRoomNumber(), deserializedStudents.get(i).getRoom().getRoomNumber());
        }
    }

    @Test
    void testDeserializeNonExistentFile() {
        List<Student> students = yamlDeserializeService.deserialize("non_existent_file.yaml");
        assertNull(students);
    }

    @Test
    void testDeserializeEmptyFile() throws IOException {
        File emptyFile = File.createTempFile("empty_students", ".yaml");
        emptyFile.deleteOnExit();

        List<Student> students = yamlDeserializeService.deserialize(emptyFile.getPath());
        assertNull(students);
    }
}
