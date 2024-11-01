package dormitories.Services.Serializers.Json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dormitories.Models.Student;
import dormitories.Services.Serializers.IDesirialize;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonDeserializeService implements IDesirialize<Student> {
    private final Gson gson;;

    public JsonDeserializeService() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public List<Student> deserialize(String filePath) {
        var students = new ArrayList<Student>();

        try (FileReader file = new FileReader(filePath)) {
            Type listType = new TypeToken<ArrayList<Student>>(){}.getType();
            students = this.gson.fromJson(file, listType);

            System.out.println("Students deserialized from JSON " + filePath);
            return students;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return students;
    }
}
