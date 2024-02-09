package entity.animals.herbivores;

import entity.animals.Herbivore;
import providers.SettingsProvider;

public class Deer extends Herbivore {

    private static final int maxOnCell = SettingsProvider.
            getCharacteristics("Deer","maxOnCell").intValue();

    private static final String emoji = "🫎";


    @Override
    public void move() {

    }


}
