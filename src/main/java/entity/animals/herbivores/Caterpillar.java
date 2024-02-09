package entity.animals.herbivores;

import entity.animals.Herbivore;
import providers.SettingsProvider;

public class Caterpillar extends Herbivore {

    private static final int maxOnCell = SettingsProvider.
            getCharacteristics("Caterpillar","maxOnCell").intValue();

    private static final String emoji = "🐛";


    @Override
    public void move() {

    }


}
