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
    public ReproduceTask(Animal animal) {
        this.animal = animal;
    }
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                if (!animal.isAlive())
                    Thread.currentThread().interrupt();
                reproduceAction();
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    public void reproduceAction() {
        Location location = animal.getLocation();
        List<Entity> repList = location.getIsland().getAnimalOfClass(animal.getClass(), location);
        int maxOnCell;
        try {
            Field field = animal.getClass().getDeclaredField("MAX_ON_CELL");
            field.setAccessible(true);
            maxOnCell = (int) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        int maxNumber = maxOnCell - repList.size();
        if (maxNumber > 0) {
            Entity partner = repList.get(ThreadLocalRandom.current().nextInt(repList.size()));
            Entity kid = animal.reproduce(partner);
            if (kid != null) {
                location.getIsland().addKid(kid);
                Island.increaseNumberOfBorn();
            }
        }
    }
}