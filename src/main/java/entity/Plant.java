package entity;

import providers.SettingsProvider;

public class Plant extends Entity{

    private static final int maxOnCell = SettingsProvider.
            getCharacteristics("Plant","maxOnCell").intValue();
    private static final String emoji = "🌱";


}
