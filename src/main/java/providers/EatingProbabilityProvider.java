package providers;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class EatingProbabilityProvider {

    private static final String TABLE_RESOURCE = "src\\main\\resources\\EatingProbabilities.yml";
    private static final Map<String, Map<String, Integer>> table;

    static {
        try {
            table = readDataFromYaml();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getProbability(String predator, String prey) {
        return table.get(predator).get(prey);
    }

    private static Map<String, Map<String, Integer>> readDataFromYaml() throws IOException {

        Yaml yaml = new Yaml();

        try (InputStream inputStream = new FileInputStream(EatingProbabilityProvider.TABLE_RESOURCE)) {
            return yaml.load(inputStream);
        }
    }

}
