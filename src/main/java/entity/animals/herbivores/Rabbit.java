package entity.animals.herbivores;

import entity.animals.Herbivore;
import providers.SettingsProvider;

public class Rabbit extends Herbivore {
    private static final int MAX_ON_CELL = SettingsProvider.
            getCharacteristics("Rabbit","maxOnCell").intValue();
    private static final String EMOJI = "🐇";
}