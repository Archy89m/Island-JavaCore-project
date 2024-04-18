package entity.animals.predators;

import entity.animals.Predator;
import providers.SettingsProvider;

public class Fox extends Predator {

    private static final int MAX_ON_CELL = SettingsProvider.
            getCharacteristics("Fox","maxOnCell").intValue();
    private static final String emoji = "🦊";



}
