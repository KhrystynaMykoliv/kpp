package dormitories.Services;

import dormitories.Entities.Student;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ManagementDormitoriesApiServiceTest {

    private final ManagementDormitoriesApiService service = new ManagementDormitoriesApiService();

    @Test
    void testDivideStudentsByPrivilege() {
        List<Student> students = getSampleStudents();
        Map<Boolean, List<Student>> result = service.divideStudentsByPrivilege(students);

        assertNotNull(result);
        assertTrue(result.containsKey(true));
        assertTrue(result.containsKey(false));
    }

    @Test
    public void testGroupStudentsByDormitories() {
        List<Student> students = getSampleStudents();
        Map<String, List<Student>> result = service.groupStudentsByDormitories(students);

        assertNotNull(result);
        assertEquals(4, result.size());
    }

    @Test
    public void testNumberStudentsByRooms() {
        List<Student> students = getSampleStudents();
        Map<Integer, Integer> result = service.numberStudentsByRooms(students);

        assertNotNull(result);
        assertEquals(10, result.size());
        assertEquals(2, (int) result.get(401)); // Room 401 should have 2 students
    }

    @Test
    public void testSortStudentsByAgeAndPrivilegies() {
        List<Student> students = getSampleStudents();
        List<Student> sortedStudents = service.sortStudentsByAgeAndPrivilegies(students);

        assertNotNull(sortedStudents);
        assertTrue(sortedStudents.get(0).getAge() <= sortedStudents.get(1).getAge());
    }

    @Test
    public void testUniqueRoomNumbers() {
        List<Student> students = getSampleStudents();
        Set<Integer> result = service.uniqueRoomNumbers(students);

        assertNotNull(result);
        assertEquals(10, result.size());
    }

    @Test
    public void testFindTheHighestAccomodationPayStudent() {
        List<Student> students = getSampleStudents();
        Optional<Student> result = service.findTheHighestAccomodationPayStudent(students);

        assertTrue(result.isPresent());
        assertEquals(700.0, result.get().getPay());
    }
    private List<Student> getSampleStudents() {
        return Arrays.asList(
                new Student("Олександр", "Михайленко", "Гуртожиток №1", 101, 550.0, 18, true),
                new Student("Іван", "Дубенко", "Гуртожиток №2", 102, 600.0, 19, false),
                new Student("Марія", "Білик", "Гуртожиток №3", 201, 650.0, 17, true),
                new Student("Олександр", "Тарасенко", "Гуртожиток №4", 301, 700.0, 20, true),
                new Student("Ольга", "Степаненко", "Гуртожиток №1", 401, 590.0, 19, false),
                new Student("Юрій", "Пилипчук", "Гуртожиток №1", 401, 640.0, 18, true),
                new Student("Світлана", "Романенко", "Гуртожиток №2", 501, 670.0, 20, false),
                new Student("Тетяна", "Іващенко", "Гуртожиток №3", 601, 700.0, 18, true),
                new Student("Дмитро", "Герасименко", "Гуртожиток №4", 701, 620.0, 19, false),
                new Student("Наталія", "Пономаренко", "Гуртожиток №2", 702, 680.0, 20, true),
                new Student("Олександр", "Шевчук", "Гуртожиток №1", 801, 600.0, 17, false)
        );
    }
}


