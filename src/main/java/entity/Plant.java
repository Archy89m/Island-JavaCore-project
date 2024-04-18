package entity;

import providers.SettingsProvider;

public class Plant extends Entity{
    private static final int MAX_ON_CELL = SettingsProvider.
            getCharacteristics("Plant","maxOnCell").intValue();
    private static final String EMOJI = "🌱";
}