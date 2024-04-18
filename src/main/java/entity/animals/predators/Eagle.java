package entity.animals.predators;

import entity.animals.Predator;
import providers.SettingsProvider;

public class Eagle extends Predator {

    private static final int MAX_ON_CELL = SettingsProvider.
            getCharacteristics("Eagle","maxOnCell").intValue();
    private static final String EMOJI = "🦅";



}
