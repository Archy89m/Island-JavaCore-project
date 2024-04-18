package providers;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class SettingsProvider {
    private static final String EATING_PROBABILITIES = "src\\main\\resources\\EatingProbabilities.yml";
    private static final String ENTITY_CHARACTERISTICS = "src\\main\\resources\\EntityCharacteristics.yml";
    private static final Map<String, Map<String, Number>> TABLE_EATING_PROBABILITIES;
    private static final Map<String, Map<String, Number>> TABLE_ENTITY_CHARACTERISTICS;

    static {
        try {
            TABLE_EATING_PROBABILITIES = readDataFromYaml(EATING_PROBABILITIES);
            TABLE_ENTITY_CHARACTERISTICS = readDataFromYaml(ENTITY_CHARACTERISTICS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Number getEatingProbability(String predator, String prey) {
        return TABLE_EATING_PROBABILITIES.get(predator).get(prey);
    }
    public static Number getCharacteristics(String entity, String characteristic) {
        return TABLE_ENTITY_CHARACTERISTICS.get(entity).get(characteristic);
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
    public static List<String> getListHerbivoreAsFoodForHerbivores() {
        List<Class<?>> herbivoresClasses = null;
        try {
            PackageScanner scanner = new PackageScanner();
            herbivoresClasses = scanner.getClasses("entity.animals.herbivores");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<String> listHerbivores = herbivoresClasses.stream()
                .map(Class::getSimpleName)
                .toList();
        List<String> result = new ArrayList<>();
        for (String predator : TABLE_EATING_PROBABILITIES.keySet()) {
            if (listHerbivores.contains(predator)) {
                Map<String, Number> preyProbabilities = TABLE_EATING_PROBABILITIES.get(predator);

                for (String prey : preyProbabilities.keySet()) {
                    if (listHerbivores.contains(prey) && preyProbabilities.get(prey).intValue() > 0)
                        if (!result.contains(prey))
                            result.add(prey);
                }
            }
        }
        return result;
    }
}