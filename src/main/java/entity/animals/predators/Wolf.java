package entity.animals.predators;

import entity.animals.Predator;
import providers.SettingsProvider;

public class Wolf extends Predator {

    private static final int maxOnCell = SettingsProvider.
            getCharacteristics("Wolf","maxOnCell").intValue();
    private static final String emoji = "🐺";



}
