package dormitories.Services.IOThreads.Room;

import dormitories.Models.Room;
import dormitories.Services.IOThreads.IRead;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoomsReadDataService implements IRead<Room> {
    @Override
    public List<Room> ReadFromFile(String filePath) {
        List<Room> rooms = new ArrayList<>();

        try (DataInputStream room = new DataInputStream(new FileInputStream(filePath))) {
            int size = room.readInt();

            for (int i = 0; i < size; i++) {
                int roomNumber = room.readInt();
                rooms.add(new Room(roomNumber));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rooms;
    }
}
