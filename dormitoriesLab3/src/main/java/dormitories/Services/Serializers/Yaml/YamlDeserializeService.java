package dormitories.Services.Serializers.Yaml;

import dormitories.Models.Student;
import dormitories.Services.Serializers.IDesirialize;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class YamlDeserializeService implements IDesirialize<Student> {
    private final Yaml yaml;

    public YamlDeserializeService() {
        Constructor constructor = new Constructor(List.class);
        this.yaml = new Yaml(constructor);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Student> deserialize(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            List<Student> students = yaml.load(reader);
            System.out.println("Students deserialized from YAML file: " + filePath);
            return students;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
