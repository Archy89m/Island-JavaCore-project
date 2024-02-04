package entity.animals.herbivores;

import entity.animals.Herbivore;
import providers.SettingsProvider;

public class Rabbit extends Herbivore {

    private static final int maxOnCell = SettingsProvider.
            getCharacteristics("Rabbit","maxOnCell").intValue();
    private static final String emoji = "🐇";

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
