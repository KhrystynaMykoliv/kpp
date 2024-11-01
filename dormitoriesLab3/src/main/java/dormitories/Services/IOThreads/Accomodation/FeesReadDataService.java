package dormitories.Services.IOThreads.Accomodation;

import dormitories.Models.Fee;
import dormitories.Services.IOThreads.IRead;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FeesReadDataService implements IRead<Fee> {
    @Override
    @SuppressWarnings("unchecked")
    public List<Fee> ReadFromFile(String filename) {
        List<Fee> fees = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(filename)))) {
            fees = (List<Fee>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return fees;
    }
}
