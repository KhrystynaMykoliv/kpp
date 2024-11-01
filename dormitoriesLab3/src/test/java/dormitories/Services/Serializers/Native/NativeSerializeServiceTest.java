package dormitories.Services.Serializers.Native;

import dormitories.Models.Fee;
import dormitories.Models.Room;
import dormitories.Models.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NativeSerializeServiceTest {
    private NativeSerializeService nativeSerializeService;
    private File tempFile;

    @BeforeEach
    void setUp() throws IOException {
        nativeSerializeService = new NativeSerializeService();
        tempFile = File.createTempFile("students", ".dat");
    }

    @AfterEach
    void tearDown() {
        tempFile.delete();
    }

    @Test
    void testSerializeWithRoomNumberBelow100() throws IOException, ClassNotFoundException {
        List<Student> students = List.of(
                new Student("John", "Doe", new Room(50), new Fee(300, false)),
                new Student("Jane", "Smith", new Room(75), new Fee(500, true))
        );

        nativeSerializeService.serialize(students, tempFile.getPath());

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tempFile))) {
            List<Student> deserializedStudents = new ArrayList<>();
            while (true) {
                try {
                    Student student = (Student) ois.readObject();
                    deserializedStudents.add(student);
                } catch (IOException e) {
                    break;
                }
            }

            assertEquals(2, deserializedStudents.size(), "Повинні серіалізуватися лише студенти з кімнатним номером < 100");

            assertEquals("John", deserializedStudents.get(0).getFirstName());
            assertEquals(50, deserializedStudents.get(0).getRoom().getRoomNumber());
            assertEquals("Jane", deserializedStudents.get(1).getFirstName());
            assertEquals(75, deserializedStudents.get(1).getRoom().getRoomNumber());
        }
    }
}
