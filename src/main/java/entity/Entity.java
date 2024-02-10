package entity;

import org.island.Island;
import providers.SettingsProvider;

public abstract class Entity {
    private final double weight;
    private volatile boolean isAlive;

    public Entity() {

        this.weight = SettingsProvider.getCharacteristics(
                this.getClass().getSimpleName(),
                "weight").doubleValue();
        this.isAlive = true;

    }

    public double getWeight() {
        return weight;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void die() {
        isAlive = false;
        Island.increaseNumberOfDeaths();
    }
}
