package tasks;

import entity.Animal;
import entity.Entity;
import entity.animals.Herbivore;
import entity.animals.Predator;
import org.island.Location;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class MovingTask implements Runnable{

    private final Animal animal;
    private final Location location;

    public MovingTask(Animal animal, Location location) {
        this.animal = animal;
        this.location = location;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                if (!animal.isAlive())
                    Thread.currentThread().interrupt();
                movingAction();
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void movingAction() {
        animal.move(location);
    }
}
