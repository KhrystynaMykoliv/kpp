package dormitories.Services.IOThreads.Accomodation;

import dormitories.Models.Fee;
import dormitories.Models.Student;
import dormitories.Services.IOThreads.IWrite;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class FeesWriteDataService implements IWrite {
    @Override
    public void writeToFile(List<Student> students, String filePath) {
        List<Fee> fees = students.stream().map(Student::getFee).collect(Collectors.toList());

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(filePath)))) {
            oos.writeObject(fees);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
