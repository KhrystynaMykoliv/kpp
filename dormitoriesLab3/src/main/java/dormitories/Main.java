package dormitories;

import dormitories.Models.Student;
import dormitories.Models.Fee;
import dormitories.Models.Room;
import dormitories.Seeds.FilePath;
import dormitories.Seeds.Students;
import dormitories.Views.SerializersView;
import dormitories.Views.StudentsView;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Student> students = Students.getStudents();
        StudentsView studentsView = new StudentsView();
        SerializersView serializersView = new SerializersView();

        studentsView.writeFeesToFile(students);
        studentsView.writeRoomsToFile(students);

        List<Fee> fees = studentsView.readFeesFromFile();
        List<Room> rooms = studentsView.readRoomsFromFile();

        serializersView.serializeStudentsNative(students, FilePath.STUDENTS_FILE_NATIVE);
        List<Student> nativeDeserializedStudents = serializersView.deserializeStudentsNative(FilePath.STUDENTS_FILE_NATIVE);
        System.out.println("\nNative Deserialized Students:");
        nativeDeserializedStudents.forEach(System.out::println);

        serializersView.serializeStudentsJson(students, FilePath.STUDENTS_FILE_JSON);
        List<Student> jsonDeserializedStudents = serializersView.deserializeStudentsJson(FilePath.STUDENTS_FILE_JSON);
        System.out.println("\nJSON Deserialized Students:");
        jsonDeserializedStudents.forEach(System.out::println);

        // YAML serialization
        serializersView.serializeStudentsYaml(students, FilePath.STUDENTS_FILE_YAML);
        List<Student> yamlDeserializedStudents = serializersView.deserializeStudentsYaml(FilePath.STUDENTS_FILE_YAML);
        System.out.println("\nYAML Deserialized Students:");
        yamlDeserializedStudents.forEach(System.out::println);
    }
}
