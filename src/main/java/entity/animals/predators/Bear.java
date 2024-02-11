package entity.animals.predators;

import entity.animals.Predator;
import providers.SettingsProvider;

public class Bear extends Predator {

    private static final int maxOnCell = SettingsProvider.
            getCharacteristics("Bear","maxOnCell").intValue();
    private static final String emoji = "🐻";



}
