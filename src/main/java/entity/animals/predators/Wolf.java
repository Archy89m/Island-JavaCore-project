package entity.animals.predators;

import entity.animals.Predator;
import providers.SettingsProvider;

public class Wolf extends Predator {
    private static final int MAX_ON_CELL = SettingsProvider.
            getCharacteristics("Wolf","maxOnCell").intValue();
    private static final String EMOJI = "🐺";
}