package dormitories.Views;

import dormitories.Models.Fee;
import dormitories.Models.Room;
import dormitories.Models.Student;
import dormitories.Seeds.FilePath;
import dormitories.Services.IOThreads.Accomodation.FeesReadDataService;
import dormitories.Services.IOThreads.Accomodation.FeesWriteDataService;
import dormitories.Services.IOThreads.Room.RoomsReadDataService;
import dormitories.Services.IOThreads.Room.RoomsWriteDataService;
import dormitories.Services.IOThreads.IRead;
import dormitories.Services.IOThreads.IWrite;

import java.util.List;

public class StudentsView {
    private final IWrite feeWriteService;
    private final IRead<Fee> feeReadService;
    private final IWrite roomWriteService;
    private final IRead<Room> roomReadService;

    public StudentsView() {
        this.feeWriteService = new FeesWriteDataService();
        this.feeReadService = new FeesReadDataService();
        this.roomWriteService = new RoomsWriteDataService();
        this.roomReadService = new RoomsReadDataService();
    }

    public void writeFeesToFile(List<Student> students) {
        feeWriteService.writeToFile(students, FilePath.FEES_FILE);
        System.out.println("Fees written to file: " + FilePath.FEES_FILE);
    }

    public List<Fee> readFeesFromFile() {
        List<Fee> fees = feeReadService.ReadFromFile(FilePath.FEES_FILE);
        System.out.println("\nFees read from file:");
        for (Fee fee : fees) {
            System.out.println(fee);
        }
        return fees;
    }

    public void writeRoomsToFile(List<Student> students) {
        roomWriteService.writeToFile(students, FilePath.ROOMS_FILE);
        System.out.println("Rooms written to file: " + FilePath.ROOMS_FILE);
    }

    public List<Room> readRoomsFromFile() {
        List<Room> rooms = roomReadService.ReadFromFile(FilePath.ROOMS_FILE);
        System.out.println("\nRooms read from file:");
        for (Room room : rooms) {
            System.out.println(room);
        }
        return rooms;
    }
}
