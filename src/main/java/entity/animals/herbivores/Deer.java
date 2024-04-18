package entity.animals.herbivores;

import entity.animals.Herbivore;
import providers.SettingsProvider;

public class Deer extends Herbivore {

    private static final int MAX_ON_CELL = SettingsProvider.
            getCharacteristics("Deer","maxOnCell").intValue();

    private static final String emoji = "🫎";



}
