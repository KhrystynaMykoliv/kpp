package dormitories.Services;

import java.util.*;
import java.util.stream.*;
import dormitories.Entities.Student;

public class ManagementDormitoriesApiService implements IDormitoriesActions {
    public Map<Boolean, List<Student>> divideStudentsByPrivilege(List<Student> students) {
        return students.stream().collect(Collectors.partitioningBy(Student::isPrivileged));
    }

    public Map<String, List<Student>> groupStudentsByDormitories(List<Student> students) {
        return students.stream().collect(Collectors.groupingBy(Student::getDormitory));
    }

    public Map<Integer, Integer> numberStudentsByRooms(List<Student> students) {
        return students.stream()
                .collect(Collectors.groupingBy(
                        Student::getRoomNumber,
                        Collectors.collectingAndThen(
                                Collectors.counting(),
                                Long::intValue
                        )));
    }

    public List<Student> sortStudentsByAgeAndPrivilegies(List<Student> students) {
        return students.stream().sorted(Comparator.comparing(Student::getAge)
                        .thenComparing(Student::isPrivileged))
                .collect(Collectors.toList());
    }

    public Set<Integer> uniqueRoomNumbers(List<Student> students) {
        return students.stream().map(Student::getRoomNumber).collect(Collectors.toSet());
    }

    public Optional<Student> findTheHighestAccomodationPayStudent(List<Student> students) {
        return students.stream().max(Comparator.comparingDouble(Student::getPay));
    }
}
