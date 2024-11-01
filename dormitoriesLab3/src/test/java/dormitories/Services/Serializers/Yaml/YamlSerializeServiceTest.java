package dormitories.Services.Serializers.Yaml;

import dormitories.Models.Fee;
import dormitories.Models.Room;
import dormitories.Models.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class YamlSerializeServiceTest {
    private YamlSerializeService yamlSerializeService;
    private File tempFile;

    @BeforeEach
    void setUp() throws IOException {
        yamlSerializeService = new YamlSerializeService();
        tempFile = File.createTempFile("students", ".yaml");
    }

    @AfterEach
    void tearDown() {
        tempFile.delete();
    }

    @Test
    void testSerializeWithDiscountFee() throws IOException {
        List<Student> students = Arrays.asList(
                new Student("John", "Doe", new Room(101), new Fee(300, true)),
                new Student("Jane", "Smith", new Room(102), new Fee(500, false))
        );

        yamlSerializeService.serialize(students, tempFile.getPath());

        try (FileReader reader = new FileReader(tempFile)) {
            Yaml yaml = new Yaml();
            List<Student> deserializedData = yaml.load(reader);

            assertNotNull(deserializedData);
            assertEquals(2, deserializedData.size());

            Student student1 = deserializedData.get(0);
            assertEquals("John", student1.getFirstName());
            assertNull(student1.getFee(), "Fee має бути null, коли є знижка");

            Student student2 = deserializedData.get(1);
            assertEquals("Jane", student2.getFirstName());
            assertNotNull(student2.getFee(), "Fee не має бути null, коли знижка відсутня");
            assertEquals(500, student2.getFee().getAmount());
            assertFalse(student2.getFee().getDiscount());
        }
    }

    @Test
    void testSerializeWithoutDiscountFee() throws IOException {
        List<Student> students = Collections.singletonList(
                new Student("Alice", "Johnson", new Room(201), new Fee(400, false))
        );

        yamlSerializeService.serialize(students, tempFile.getPath());

        try (FileReader reader = new FileReader(tempFile)) {
            Yaml yaml = new Yaml();
            List<Student> deserializedData = yaml.load(reader);

            assertNotNull(deserializedData);
            assertEquals(1, deserializedData.size());

            Student student = deserializedData.get(0);
            assertEquals("Alice", student.getFirstName());
            assertNotNull(student.getFee(), "Fee не має бути null");
            assertEquals(400, student.getFee().getAmount());
            assertFalse(student.getFee().getDiscount());
        }
    }

    @Test
    void testSerializeEmptyList() throws IOException {
        List<Student> students = Collections.emptyList();
        yamlSerializeService.serialize(students, tempFile.getPath());

        try (FileReader reader = new FileReader(tempFile)) {
            Yaml yaml = new Yaml();
            List<?> deserializedData = yaml.load(reader);

            assertNotNull(deserializedData, "Список має бути пустим");
            assertTrue(deserializedData.isEmpty(), "Десеріалізовані дані мають бути порожнім списком");
        }
    }
}
