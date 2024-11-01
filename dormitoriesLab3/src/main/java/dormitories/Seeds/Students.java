package dormitories.Seeds;

import dormitories.Models.Student;
import dormitories.Models.Room;
import dormitories.Models.Fee;

import java.util.ArrayList;
import java.util.List;

public class Students {
    public static List<Student> getStudents() {
        List<Student> students = new ArrayList<>();

        students.add(new Student("John", "Doe", new Room(91), new Fee(5000, true)));
        students.add(new Student("Jane", "Doe", new Room(102), new Fee(5500, false)));
        students.add(new Student("Alice", "Johnson", new Room(103), new Fee(5200, false)));
        students.add(new Student("Bob", "Smith", new Room(104), new Fee(5300, false)));
        students.add(new Student("Charlie", "Brown", new Room(105), new Fee(5100, true)));
        students.add(new Student("David", "Wilson", new Room(106), new Fee(5400, false)));
        students.add(new Student("Eve", "Davis", new Room(77), new Fee(5600, false)));
        students.add(new Student("Frank", "Miller", new Room(108), new Fee(5000, false)));
        students.add(new Student("Grace", "Taylor", new Room(109), new Fee(5300, false)));
        students.add(new Student("Hank", "Moore", new Room(110), new Fee(5700, true)));

        return students;
    }
}
