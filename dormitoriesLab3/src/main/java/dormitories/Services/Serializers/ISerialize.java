package dormitories.Services.Serializers;

import java.util.List;

public interface ISerialize<T> {
    public void serialize(List<T> students, String filePath);
}
