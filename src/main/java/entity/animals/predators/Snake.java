package entity.animals.predators;

import entity.animals.Predator;
import providers.SettingsProvider;

public class Snake extends Predator {

    private static final int maxOnCell = SettingsProvider.
            getCharacteristics("Snake","maxOnCell").intValue();
    private static final String emoji = "🐍";



    @Override
    public void move() {

    }

    @Override
    public void reproduce() {

    }
}
