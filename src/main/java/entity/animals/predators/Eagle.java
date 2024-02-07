package entity.animals.predators;

import entity.animals.Predator;
import providers.SettingsProvider;

public class Eagle extends Predator {

    private static final int maxOnCell = SettingsProvider.
            getCharacteristics("Eagle","maxOnCell").intValue();
    private static final String emoji = "🦅";



    @Override
    public void move() {

    }

    @Override
    public void reproduce() {

    }
}
