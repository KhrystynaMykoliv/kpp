package dormitories.Services;

import dormitories.Entities.Student;
import java.util.*;

public interface IDormitoriesActions {
     Map<Boolean, List<Student>> divideStudentsByPrivilege(List<Student> students);
     Map<String, List<Student>> groupStudentsByDormitories(List<Student> students);
     Map<Integer, Integer> numberStudentsByRooms(List<Student> students);
     List<Student> sortStudentsByAgeAndPrivilegies(List<Student> students);
     Set<Integer> uniqueRoomNumbers(List<Student> students);
     Optional<Student> findTheHighestAccomodationPayStudent(List<Student> students);
}