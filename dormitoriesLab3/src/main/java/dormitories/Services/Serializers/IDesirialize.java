package dormitories.Services.Serializers;

import java.util.List;

public interface IDesirialize<T> {
    List<T> deserialize(String filePath);
}
