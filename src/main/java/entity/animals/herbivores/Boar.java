package entity.animals.herbivores;

import entity.animals.Herbivore;
import providers.SettingsProvider;

import java.util.List;

public class Boar extends Herbivore {

    private static final int maxOnCell = SettingsProvider.
            getCharacteristics("Boar","maxOnCell").intValue();

    private static final String emoji = "🐗";


    @Override
    public void move() {

    }

    @Override
    public void reproduce() {

    }



}
