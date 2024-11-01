package dormitories.Services.Serializers.Json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dormitories.Models.Student;
import dormitories.Services.Serializers.ISerialize;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonSerializeService implements ISerialize<Student> {
    private final Gson gson;;

    public JsonSerializeService() {
        this.gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void serialize(List<Student> students, String filePath) {
        try (FileWriter file = new FileWriter(filePath)) {
            this.gson.toJson(students, file);
            System.out.println("Students serialized to JSON " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
