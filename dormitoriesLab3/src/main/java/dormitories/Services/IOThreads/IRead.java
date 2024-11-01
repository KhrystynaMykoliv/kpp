package dormitories.Services.IOThreads;

import java.util.List;

public interface IRead<T> {
    List<T> ReadFromFile(String filename);
}
