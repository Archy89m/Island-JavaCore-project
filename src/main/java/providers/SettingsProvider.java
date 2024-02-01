package providers;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SettingsProvider {

    private static final String EATING_PROBABILITIES = "src\\main\\resources\\EatingProbabilities.yml";
    private static final String ENTITY_CHARACTERISTICS = "src\\main\\resources\\EntityCharacteristics.yml";
    private static final Map<String, Map<String, Number>> tableEatingProbabilities;
    private static final Map<String, Map<String, Number>> tableEntityCharacteristics;

    static {
        try {
            tableEatingProbabilities = readDataFromYaml(EATING_PROBABILITIES);
            tableEntityCharacteristics = readDataFromYaml(ENTITY_CHARACTERISTICS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Number getEatingProbability(String predator, String prey) {
        return tableEatingProbabilities.get(predator).get(prey);
    }

    public static Number getCharacteristics(String entity, String characteristic) {
        return tableEntityCharacteristics.get(entity).get(characteristic);
    }

    private static Map<String, Map<String, Number>> readDataFromYaml(String fileName) throws IOException {

        LinkedHashMap<String, Map<String, ?>> originalMap;
        Yaml yaml = new Yaml();
        try (InputStream inputStream = new FileInputStream(fileName)) {
            originalMap = yaml.load(inputStream);
        }

        HashMap<String, Map<String, Number>> finalMap = new HashMap<>();
        for (Map.Entry<String, Map<String, ?>> entry : originalMap.entrySet()) {
            String key = entry.getKey();
            Map<String, ?> innerMap = entry.getValue();

            Map<String, Number> newInnerMap = new HashMap<>();
            for (Map.Entry<String, ?> innerEntry : innerMap.entrySet()) {
                String innerKey = innerEntry.getKey();
                Double innerValue = (innerEntry.getValue() instanceof Number)
                        ? ((Number) innerEntry.getValue()).doubleValue()
                        : null;
                newInnerMap.put(innerKey, innerValue);
            }
            finalMap.put(key, newInnerMap);
        }
        return finalMap;
    }
}