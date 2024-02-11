package entity;

import entity.animals.Herbivore;
import org.island.Island;
import org.island.Location;
import providers.EntityFactory;
import providers.SettingsProvider;
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

        int distance = ThreadLocalRandom.current().nextInt(movementSpeed + 1);

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
            currentLocation.getIsland().moveAnimal(this, currentLocation.getI(), currentLocation.getJ(), newI, newJ);
    }

    public Entity reproduce(Entity partner) {

        boolean sex = ThreadLocalRandom.current().nextBoolean();
        if (sex)
            return EntityFactory.reproduceAnimals(partner.getClass());
        else
            return null;
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

    public void hunger() {
        if (hungerLevel <= 0 && isAlive()) {
            die();
            return;
        }
        double hungerCount = amountFoodForSatiety / 10;
        hungerLevel = hungerLevel - hungerCount;
    }

}
