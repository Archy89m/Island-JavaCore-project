package entity;

import entity.animals.Herbivore;
import org.island.Location;
import providers.EntityFactory;
import providers.SettingsProvider;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Entity {

    private final int MOVEMENT_SPEED;
    private final double AMOUNT_FOOD_FOR_SATIETY;
    private volatile double hungerLevel;
    private final int HUNGER_RATE = 3;

    public Animal() {

        super();

        this.MOVEMENT_SPEED = SettingsProvider.getCharacteristics(
                this.getClass().getSimpleName(),
                "movementSpeed").intValue();
        this.AMOUNT_FOOD_FOR_SATIETY = SettingsProvider.getCharacteristics(
                this.getClass().getSimpleName(),
                "amountFoodForSatiety").doubleValue();
        this.hungerLevel = this.AMOUNT_FOOD_FOR_SATIETY;
    }

    public void eat(Entity food) {

        if (hungerLevel >= AMOUNT_FOOD_FOR_SATIETY)
            return;
        if (food instanceof Herbivore || food instanceof Plant) {

            Number num = SettingsProvider.getEatingProbability(this.getClass().getSimpleName(), food.getClass().getSimpleName());
            int chance = num.intValue();

            int rand = ThreadLocalRandom.current().nextInt(1, 101);

            if (rand <= chance && food.isAlive()) {
                hungerLevel = hungerLevel + food.getWeight();
                food.die();
            }
        }
    }

    public void move() {

        Location currentLocation = getLocation();

        int iMax = currentLocation.getIsland().getRows() - 1;
        int jMax = currentLocation.getIsland().getCols() - 1;
        int newI = currentLocation.getI();
        int newJ = currentLocation.getJ();
        int distance = ThreadLocalRandom.current().nextInt(MOVEMENT_SPEED + 1);

        for (int i = 0; i < distance; i++) {
            if (ThreadLocalRandom.current().nextBoolean()) {
                newI++;
                if (newI > iMax)
                    newI = 0;
            } else {
                newJ++;
                if (newJ > jMax)
                    newJ = 0;
            }
        }

        if (newI != currentLocation.getI() || newJ != currentLocation.getJ())
            setLocation(currentLocation.getIsland().getLocation(newI, newJ));
    }

    public Optional<Entity> reproduce(Entity partner) {

        boolean sex = ThreadLocalRandom.current().nextBoolean();

        if (sex)
            return Optional.ofNullable(EntityFactory.reproduceAnimals(partner.getClass()));
        else
            return Optional.empty();
    }

    public void hunger() {

        if (hungerLevel <= 0 && isAlive()) {
            die();
            return;
        }
        double hungerCount = AMOUNT_FOOD_FOR_SATIETY / HUNGER_RATE;
        hungerLevel = hungerLevel - hungerCount;
    }
}