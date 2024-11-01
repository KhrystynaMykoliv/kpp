package dormitories.Services.IOThreads;

import dormitories.Models.Student;

import java.util.List;

public interface IWrite {
    public void writeToFile(List<Student> students, String filePath);
}
