package dormitories.Services.IOThreads.Room;

import dormitories.Models.Room;
import dormitories.Models.Student;
import dormitories.Services.IOThreads.IWrite;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class RoomsWriteDataService implements IWrite {
    @Override
    public void writeToFile(List<Student> students, String filePath) {
        List<Room> rooms = students.stream().map(Student::getRoom).collect(Collectors.toList());

        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(filePath))) {
            out.writeInt(rooms.size());

            for (Room room : rooms) {
                out.writeInt(room.getRoomNumber());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
