package dormitories.Services.Serializers.Json;

import dormitories.Models.Student;
import dormitories.Seeds.Students;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonDeserializeServiceTest {
    private JsonDeserializeService jsonDeserializeService;
    private JsonSerializeService jsonSerializeService;
    private File tempFile;

    @BeforeEach
    void setUp() throws IOException {
        jsonDeserializeService = new JsonDeserializeService();
        jsonSerializeService = new JsonSerializeService();
        tempFile = File.createTempFile("students", ".json");
    }

    @AfterEach
    void tearDown() {
        tempFile.delete();
    }

    @Test
    void testDeserialize() throws IOException {
        List<Student> originalStudents = Students.getStudents();

        jsonSerializeService.serialize(originalStudents, tempFile.getPath());

        List<Student> deserializedStudents = jsonDeserializeService.deserialize(tempFile.getPath());

        assertEquals(originalStudents.size(), deserializedStudents.size());
        for (int i = 0; i < originalStudents.size(); i++) {
            assertEquals(originalStudents.get(i).getFirstName(), deserializedStudents.get(i).getFirstName());
            assertEquals(originalStudents.get(i).getLastName(), deserializedStudents.get(i).getLastName());
            assertEquals(originalStudents.get(i).getFee().getAmount(), deserializedStudents.get(i).getFee().getAmount());
            assertEquals(originalStudents.get(i).getFee().getDiscount(), deserializedStudents.get(i).getFee().getDiscount());
        }
    }
}
