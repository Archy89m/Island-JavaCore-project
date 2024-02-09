package entity;

import entity.animals.Herbivore;
import org.island.Location;
import providers.EntityFactory;
import providers.SettingsProvider;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Entity {

    private final int movementSpeed;
    private final double amountFoodForSatiety;
    private volatile double hungerLevel;

    public void eat(Entity food) {

        if (hungerLevel >= amountFoodForSatiety)
            return;

        if (food instanceof Herbivore || food instanceof Plant) {

            Number num = SettingsProvider.getEatingProbability(this.getClass().getSimpleName(), food.getClass().getSimpleName());
            int chance = num.intValue();

            int rand = ThreadLocalRandom.current().nextInt(1, 101);

            if (rand <= chance) {
                hungerLevel = hungerLevel + food.getWeight();
                food.die();
            }
        }
    }

    public abstract void move();

    public List<Entity> reproduce(Entity partner, Location location) {

        boolean sex = ThreadLocalRandom.current().nextBoolean();
        List<Entity> kids = null;
        if (sex) {
            int numberOfKids = ThreadLocalRandom.current().nextInt(1, 6);
            kids = EntityFactory.reproduceAnimals(partner.getClass(), numberOfKids);
        }
        return kids;
    }

    public Animal() {

        super();

        this.movementSpeed = SettingsProvider.getCharacteristics(
                this.getClass().getSimpleName(),
                "movementSpeed").intValue();
        this.amountFoodForSatiety = SettingsProvider.getCharacteristics(
                this.getClass().getSimpleName(),
                "amountFoodForSatiety").doubleValue();
        this.hungerLevel = this.amountFoodForSatiety;

    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public double getAmountFoodForSatiety() {
        return amountFoodForSatiety;
    }

    public double getHungerLevel() {
        return hungerLevel;
    }

    public void hunger() {
        if (hungerLevel <= 0)
            die();
        double hungerCount = amountFoodForSatiety / 10;
        hungerLevel = hungerLevel - hungerCount;
    }

}
