package entity;

import entity.Entity;
import providers.SettingsProvider;

public abstract class Animal extends Entity {

    private int movementSpeed;
    private int amountFoodForSatiety;

    public abstract void eat(Object food);

    public abstract void move();

    public abstract void reproduce();

    public Animal() {

        super();

        this.movementSpeed = SettingsProvider.getCharacteristics(
                this.getClass().getSimpleName(),
                "movementSpeed").intValue();
        this.amountFoodForSatiety = SettingsProvider.getCharacteristics(
                this.getClass().getSimpleName(),
                "amountFoodForSatiety").intValue();
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public int getAmountFoodForSatiety() {
        return amountFoodForSatiety;
    }

}
