package tasks;

import entity.Animal;
import entity.Entity;
import org.island.Island;
import org.island.Location;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ReproduceTask implements Runnable{
    private final Animal animal;
    private final Location location;

    public ReproduceTask(Animal animal, Location location) {
        this.animal = animal;
        this.location = location;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                if (!animal.isAlive())
                    Thread.currentThread().interrupt();
                reproduceAction();
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void reproduceAction() {

        List<Entity> repList = location.getAnimalOfClass(animal.getClass());
        int maxOnCell;

        try {
            Field field = animal.getClass().getDeclaredField("maxOnCell");
            field.setAccessible(true);
            maxOnCell = (int) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        int maxNumber = maxOnCell - repList.size();

        if (maxNumber > 0) {
            Entity partner = repList.get(ThreadLocalRandom.current().nextInt(repList.size()));

            Entity kid = animal.reproduce(partner, location);
            if (kid != null) {
                location.addKid(kid);
                Island.increaseNumberOfBorn();
            }
        }
    }
}
