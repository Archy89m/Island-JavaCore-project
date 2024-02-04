package entity;

import providers.SettingsProvider;

public abstract class Entity {
    private double weight;

    public Entity() {

        this.weight = SettingsProvider.getCharacteristics(
                this.getClass().getSimpleName(),
                "weight").doubleValue();

    }

    public double getWeight() {
        return weight;
    }

}
