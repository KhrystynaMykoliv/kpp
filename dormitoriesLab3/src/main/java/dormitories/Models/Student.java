package dormitories.Models;

import java.io.Serializable;
import com.google.gson.annotations.Expose;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    private Room room;
    @Expose
    private Fee fee;

    // No-argument constructor
    public Student() {}

    // Parameterized constructor
    public Student(String firstName, String lastName, Room room, Fee fee) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.room = room;
        this.fee = fee;
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }

    public Fee getFee() { return fee; }
    public void setFee(Fee fee) { this.fee = fee; }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", room=" + room +
                ", fee=" + fee +
                '}';
    }
}
