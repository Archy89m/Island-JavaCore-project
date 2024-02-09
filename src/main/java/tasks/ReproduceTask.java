package tasks;

import entity.Animal;
import entity.Entity;
import org.island.Location;

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
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void reproduceAction() {

        List<Entity> repList = location.getAnimalOfClass(animal.getClass());
        Entity partner = repList.get(ThreadLocalRandom.current().nextInt(repList.size()));

        List<Entity> kids = animal.reproduce(partner, location);
        if (!kids.isEmpty())
            location.addEntities(kids);
    }
}
