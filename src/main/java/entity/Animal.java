package entity;

import entity.Entity;
import entity.animals.Herbivore;
import entity.animals.Predator;
import providers.SettingsProvider;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Entity {

    private final int movementSpeed;
    private final int amountFoodForSatiety;
    private int hungerLevel;

    public boolean eat(Entity food) {

        if (food instanceof Herbivore || food instanceof Plant) {

            Number num = SettingsProvider.getEatingProbability(this.getClass().getSimpleName(), food.getClass().getSimpleName());
            int chance = num.intValue();

            int rand = ThreadLocalRandom.current().nextInt(1, 101);
            if (rand <= chance) {
                food.die();
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

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
