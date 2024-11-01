package dormitories.Services.Serializers.Yaml;

import dormitories.Models.Fee;
import dormitories.Models.Student;
import dormitories.Services.Serializers.ISerialize;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class YamlSerializeService implements ISerialize<Student> {
    private final Yaml yaml;

    public YamlSerializeService() {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setIndent(2);
        this.yaml = new Yaml(options);
    }

    @Override
    public void serialize(List<Student> students, String filePath) {
        List<Student> filteredStudents = students.stream()
                .map(this::filterFeeIfDiscounted)
                .collect(Collectors.toList());

        try (FileWriter writer = new FileWriter(filePath)) {
            yaml.dump(filteredStudents, writer);
            System.out.println("Students serialized to YAML file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Student filterFeeIfDiscounted(Student student) {
        if (student.getFee() != null && student.getFee().getDiscount()) {
            return new Student(student.getFirstName(), student.getLastName(), student.getRoom(), null);
        } else {
            return student;
        }
    }
}
