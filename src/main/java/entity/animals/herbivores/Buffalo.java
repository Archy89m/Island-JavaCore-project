package entity.animals.herbivores;

import entity.animals.Herbivore;
import providers.SettingsProvider;

public class Buffalo extends Herbivore {

    private static final int MAX_ON_CELL = SettingsProvider.
            getCharacteristics("Buffalo","maxOnCell").intValue();
    private static final String EMOJI = "🦬";
}