package entity.animals.herbivores;

import entity.animals.Herbivore;
import providers.SettingsProvider;

public class Mouse extends Herbivore {

    private static final int maxOnCell = SettingsProvider.
            getCharacteristics("Mouse","maxOnCell").intValue();
    private static final String emoji = "🐀";

    @Override
    public void eat(Object food) {

    }

    @Override
    public void move() {

    }

    @Override
    public void reproduce() {

    }
}
