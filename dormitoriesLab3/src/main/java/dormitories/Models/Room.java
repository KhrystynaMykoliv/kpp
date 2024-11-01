package dormitories.Models;

import java.io.Serializable;

public class Room implements Serializable {
    private static final long serialVersionUID = 1L;
    private int roomNumber;

    public Room() {}

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return "Room: " + roomNumber;
    }
}
