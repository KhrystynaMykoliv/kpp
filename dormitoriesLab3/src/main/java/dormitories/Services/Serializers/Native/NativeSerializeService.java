package dormitories.Services.Serializers.Native;

import dormitories.Models.Student;
import dormitories.Services.Serializers.ISerialize;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class NativeSerializeService implements ISerialize<Student> {
    @Override
    public void serialize(List<Student> students, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (Student student : students) {
                if (student.getRoom().getRoomNumber() < 100) {
                    oos.writeObject(student);
                }
            }

            System.out.println("Students serialized to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
