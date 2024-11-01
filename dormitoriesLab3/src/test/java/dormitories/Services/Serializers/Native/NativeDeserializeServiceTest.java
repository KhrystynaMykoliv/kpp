package dormitories.Services.Serializers.Native;

import dormitories.Models.Fee;
import dormitories.Models.Room;
import dormitories.Models.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NativeDeserializeServiceTest {
    private NativeDeserializeService nativeDeserializeService;
    private File tempFile;

    @BeforeEach
    void setUp() throws IOException {
        nativeDeserializeService = new NativeDeserializeService();
        tempFile = File.createTempFile("students", ".dat");
    }

    @AfterEach
    void tearDown() {
        tempFile.delete();
    }

    @Test
    void testDeserialize() throws IOException, ClassNotFoundException {
        List<Student> students = new ArrayList<>();
        students.add(new Student("John", "Doe", new Room(101), new Fee(300, false)));
        students.add(new Student("Jane", "Smith", new Room(102), new Fee(500, true)));

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tempFile))) {
            for (Student student : students) {
                oos.writeObject(student);
            }
        }

        List<Student> deserializedStudents = nativeDeserializeService.deserialize(tempFile.getPath());

        assertNotNull(deserializedStudents);
        assertEquals(students.size(), deserializedStudents.size());

        for (int i = 0; i < students.size(); i++) {
            Student original = students.get(i);
            Student deserialized = deserializedStudents.get(i);
            assertEquals(original.getFirstName(), deserialized.getFirstName());
            assertEquals(original.getLastName(), deserialized.getLastName());
            assertEquals(original.getRoom().getRoomNumber(), deserialized.getRoom().getRoomNumber());

            if (original.getFee() != null && deserialized.getFee() != null) {
                assertEquals(original.getFee().getAmount(), deserialized.getFee().getAmount());
                assertEquals(original.getFee().getDiscount(), deserialized.getFee().getDiscount());
            } else {
                assertNull(deserialized.getFee());
            }
        }
    }

    @Test
    void testDeserializeNonExistentFile() {
        List<Student> students = nativeDeserializeService.deserialize("non_existent_file.dat");
        assertNotNull(students);
        assertTrue(students.isEmpty(), "Список студентів має бути порожнім для неіснуючого файлу");
    }

    @Test
    void testDeserializeEmptyFile() throws IOException {
        File emptyFile = File.createTempFile("empty_students", ".dat");
        emptyFile.deleteOnExit();

        List<Student> students = nativeDeserializeService.deserialize(emptyFile.getPath());

        assertNotNull(students);
        assertTrue(students.isEmpty(), "Список студентів має бути порожнім для порожнього файлу");
    }
}
