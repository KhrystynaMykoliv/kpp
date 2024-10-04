package dormitories;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import dormitories.Entities.Student;
import dormitories.Services.*;

public class Main {
    public static void main(String[] args) {


        List<Student> students = getStudentList();

        IDormitoriesActions service = new ManagementDormitoriesService();

        Map<Boolean, List<Student>> dividedStudents = service.divideStudentsByPrivilege(students);
        System.out.println("Студенти з Пільгою:");
        printDetailedStudentList(dividedStudents.get(true));

        System.out.println("\nСтуденти без пільги:");
        printDetailedStudentList(dividedStudents.get(false));

        Map<String, List<Student>> dormitoryGroups = service.groupStudentsByDormitories(students);
        System.out.println("\nГуртожитки з студентами:");
        dormitoryGroups.forEach((dormitory, studentsOfDormitory) -> {
            System.out.println("Гуртожиток: " + dormitory);
            printDetailedStudentList(studentsOfDormitory);
        });

        Map<Integer, Integer> countStudentsByRooms = service.numberStudentsByRooms(students);
        System.out.println("\nКількість студентів у кожній кімнаті:");
        countStudentsByRooms.forEach((room, count) -> System.out.println("Кімната №" + room + ": " + count + " студентів"));

        List<Student> sortedStudents = service.sortStudentsByAgeAndPrivilegies(students);
        System.out.println("\nВідсортовані студенти за віком та пільгою:");
        printDetailedStudentList(sortedStudents);

        Set<Integer> uniqueRoomNumbers = service.uniqueRoomNumbers(students);
        System.out.println("\nУнікальні номера кімнат:");
        System.out.println("Унікальні кімнати: " + uniqueRoomNumbers);

        Optional<Student> student = service.findTheHighestAccomodationPayStudent(students);
        System.out.println("\nСтудент, який сплачує найбільшу плату за проживання:");
        System.out.println(student.map(s -> formatStudent(s)).orElse("Немає студентів"));
    }

    public static void printDetailedStudentList(List<Student> students) {
        if (students == null || students.isEmpty()) {
            System.out.println("Немає студентів у цій категорії.");
        } else {
            students.forEach(student -> System.out.println(formatStudent(student)));
        }
    }

    public static String formatStudent(Student student) {
        return String.format("Студент: %s %s | Вік: %d | Гуртожиток: %s | Кімната: %d | Плата: %.2f | Пільга: %s",
                student.getFirstName(), student.getLastName(), student.getAge(),
                student.getDormitory(), student.getRoomNumber(), student.getPay(),
                student.isPrivileged() ? "Так" : "Ні");
    }

    public static List<Student> getStudentList() {
        return Arrays.asList(
                new Student("Олександр", "Михайленко", "Гуртожиток №1", 101, 550.0, 18, true),
                new Student("Іван", "Дубенко", "Гуртожиток №1", 102, 600.0, 19, false),
                new Student("Марія", "Білик", "Гуртожиток №1", 201, 650.0, 17, true),
                new Student("Андрій", "Тарасенко", "Гуртожиток №2", 202, 620.0, 20, true),
                new Student("Ольга", "Степаненко", "Гуртожиток №1", 301, 590.0, 19, false),
                new Student("Юрій", "Пилипчук", "Гуртожиток №1", 302, 640.0, 18, true),
                new Student("Світлана", "Романенко", "Гуртожиток №2", 401, 670.0, 20, false),
                new Student("Тетяна", "Іващенко", "Гуртожиток №3", 402, 700.0, 18, true),
                new Student("Дмитро", "Герасименко", "Гуртожиток №2", 502, 620.0, 19, false),
                new Student("Наталія", "Пономаренко", "Гуртожиток №2", 502, 680.0, 20, true),
                new Student("Олександр", "Шевчук", "Гуртожиток №1", 601, 600.0, 17, false),
                new Student("Катерина", "Ярошенко", "Гуртожиток №4", 602, 690.0, 18, true),
                new Student("Віктор", "Ковальчук", "Гуртожиток №4", 602, 610.0, 19, false),
                new Student("Олена", "Горбунова", "Гуртожиток №4", 702, 710.0, 17, true),
                new Student("Ірина", "Богдан", "Гуртожиток №2", 801, 580.0, 20, false)
        );
    }
}
