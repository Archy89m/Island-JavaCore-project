package tasks;

import entity.Animal;
import entity.Entity;
import entity.animals.Herbivore;
import entity.animals.Predator;
import org.island.Location;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


public class EatingTask implements Runnable{

    private final Animal animal;
    private final Location location;

    public EatingTask(Animal animal, Location location) {
        this.animal = animal;
        this.location = location;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                if (!animal.isAlive())
                    Thread.currentThread().interrupt();
                eatingAction();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void eatingAction() {
        Entity prey = null;
        List<Entity> food = null;
        if (animal instanceof Predator) {
            food = location.getHerbivores();
        } else if (animal instanceof Herbivore) {
            food = location.getFoodForHerbivores();
        } else {
            return;
        }
        if (!food.isEmpty()) {
            prey = food.get(ThreadLocalRandom.current().nextInt(food.size()));
        } else {
            return;
        }
        animal.eat(prey);
    }
}
