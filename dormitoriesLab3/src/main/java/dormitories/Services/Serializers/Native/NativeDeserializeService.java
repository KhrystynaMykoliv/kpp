package dormitories.Services.Serializers.Native;

import dormitories.Models.Student;
import dormitories.Services.Serializers.IDesirialize;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class NativeDeserializeService implements IDesirialize<Student> {
    @Override
    public List<Student> deserialize(String filePath) {
        List<Student> students = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {

            while (true) {
                try {
                    Student item = (Student) ois.readObject();
                    students.add(item);
                } catch (EOFException e) {
                    break;
                }
            }

            System.out.println("Students deserialized from " + filePath);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return students;
    }
}
