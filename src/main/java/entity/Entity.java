package entity;

import providers.SettingsProvider;

public abstract class Entity {
    private double weight;
    private int maxOnCell;

    public Entity() {

        this.weight = SettingsProvider.getCharacteristics(
                this.getClass().getSimpleName(),
                "weight").doubleValue();

        this.maxOnCell = SettingsProvider.getCharacteristics(
                this.getClass().getSimpleName(),
                "maxOnCell").intValue();
    }

    public double getWeight() {
        return weight;
    }

    public int getMaxOnCell() {
        return maxOnCell;
    }
}
