package dormitories.Services;

import dormitories.Entities.Student;

import java.util.*;

public class ManagementDormitoriesService implements IDormitoriesActions {

    public Map<Boolean, List<Student>> divideStudentsByPrivilege(List<Student> students) {
        Map<Boolean, List<Student>> divisionMap = new HashMap<>();
        divisionMap.put(true, new ArrayList<>());
        divisionMap.put(false, new ArrayList<>());

        for (Student student : students) {
            boolean isPrivileged = student.isPrivileged();
            divisionMap.get(isPrivileged).add(student);
        }

        return divisionMap;
    }

    public Map<String, List<Student>> groupStudentsByDormitories(List<Student> students) {
        Map<String, List<Student>> dormitoriesMap = new HashMap<>();

        for (Student student : students) {
            String dormitory = student.getDormitory();
            dormitoriesMap.computeIfAbsent(dormitory, k -> new ArrayList<>()).add(student);
        }

        return dormitoriesMap;
    }

    public Map<Integer, Integer> numberStudentsByRooms(List<Student> students) {
        Map<Integer, Integer> numberStudentsMap = new HashMap<>();

        for (Student student : students) {
            int roomNumber = student.getRoomNumber();
            numberStudentsMap.put(roomNumber, numberStudentsMap.getOrDefault(student.getRoomNumber(), 0) + 1);
        }

        return numberStudentsMap;
    }

    public List<Student> sortStudentsByAgeAndPrivilegies(List<Student> students) {
        students.sort(Comparator.comparing(Student::getAge).thenComparing(Student::isPrivileged));

        return students;
    }

    public Set<Integer> uniqueRoomNumbers(List<Student> students) {
        Set<Integer> uniqueRoomNumbersHash = new HashSet<>();

        for (Student student : students) {
            uniqueRoomNumbersHash.add(student.getRoomNumber());
        }

        return uniqueRoomNumbersHash;
    }

    public Optional<Student> findTheHighestAccomodationPayStudent(List<Student> students) {
        if (students.isEmpty()) {
            return Optional.empty();
        }

        Student maxFeeStudent = students.get(0);

        for (Student student : students) {
            if (student.getPay() > maxFeeStudent.getPay()) {
                maxFeeStudent = student;
            }
        }

        return Optional.of(maxFeeStudent);
    }
}
