package entity.animals.predators;

import entity.animals.Predator;
import providers.SettingsProvider;

public class Snake extends Predator {
    private static final int MAX_ON_CELL = SettingsProvider.
            getCharacteristics("Snake","maxOnCell").intValue();
    private static final String EMOJI = "🐍";
}