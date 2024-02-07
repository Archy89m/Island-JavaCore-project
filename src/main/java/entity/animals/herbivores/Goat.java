package entity.animals.herbivores;

import entity.animals.Herbivore;
import providers.SettingsProvider;

public class Goat extends Herbivore {

    private static final int maxOnCell = SettingsProvider.
            getCharacteristics("Goat","maxOnCell").intValue();
    private static final String emoji = "🐐";


    @Override
    public void move() {

    }

    @Override
    public void reproduce() {

    }
}
