package entity.animals.herbivores;

import entity.animals.Herbivore;
import providers.SettingsProvider;

public class Duck extends Herbivore {

    private static final int MAX_ON_CELL = SettingsProvider.
            getCharacteristics("Duck","maxOnCell").intValue();

    private static final String emoji = "🦆";



}
