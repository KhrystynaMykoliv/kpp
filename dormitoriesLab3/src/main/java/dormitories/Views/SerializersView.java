package dormitories.Views;

import dormitories.Models.Student;
import dormitories.Services.Serializers.Json.JsonDeserializeService;
import dormitories.Services.Serializers.Json.JsonSerializeService;
import dormitories.Services.Serializers.Native.NativeDeserializeService;
import dormitories.Services.Serializers.Native.NativeSerializeService;
import dormitories.Services.Serializers.Yaml.YamlDeserializeService;
import dormitories.Services.Serializers.Yaml.YamlSerializeService;

import java.util.List;

public class SerializersView {
    private final NativeSerializeService nativeSerializeService;
    private final NativeDeserializeService nativeDeserializeService;
    private final JsonSerializeService jsonSerializeService;
    private final JsonDeserializeService jsonDeserializeService;
    private final YamlSerializeService yamlSerializeService;
    private final YamlDeserializeService yamlDeserializeService;

    public SerializersView() {
        this.nativeSerializeService = new NativeSerializeService();
        this.nativeDeserializeService = new NativeDeserializeService();
        this.jsonSerializeService = new JsonSerializeService();
        this.jsonDeserializeService = new JsonDeserializeService();
        this.yamlSerializeService = new YamlSerializeService();
        this.yamlDeserializeService = new YamlDeserializeService();
    }

    public void serializeStudentsNative(List<Student> students, String filePath) {
        nativeSerializeService.serialize(students, filePath);
    }

    public List<Student> deserializeStudentsNative(String filePath) {
        return nativeDeserializeService.deserialize(filePath);
    }

    public void serializeStudentsJson(List<Student> students, String filePath) {
        jsonSerializeService.serialize(students, filePath);
    }

    public List<Student> deserializeStudentsJson(String filePath) {
        return jsonDeserializeService.deserialize(filePath);
    }

    public void serializeStudentsYaml(List<Student> students, String filePath) {
        yamlSerializeService.serialize(students, filePath);
    }

    public List<Student> deserializeStudentsYaml(String filePath) {
        return yamlDeserializeService.deserialize(filePath);
    }
}
