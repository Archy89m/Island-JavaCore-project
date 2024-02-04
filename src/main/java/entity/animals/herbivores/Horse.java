package entity.animals.herbivores;

import entity.animals.Herbivore;
import providers.SettingsProvider;

public class Horse extends Herbivore {

    private static final int maxOnCell = SettingsProvider.
            getCharacteristics("Horse","maxOnCell").intValue();
    private static final String emoji = "🐎";

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
