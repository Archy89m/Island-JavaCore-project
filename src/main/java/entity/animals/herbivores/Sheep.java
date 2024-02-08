package entity.animals.herbivores;

import entity.animals.Herbivore;
import providers.SettingsProvider;

public class Sheep extends Herbivore {

    private static final int maxOnCell = SettingsProvider.
            getCharacteristics("Sheep","maxOnCell").intValue();

    private static final String emoji = "🐑";

    @Override
    public void move() {

    }

    @Override
    public void reproduce() {

    }
}
