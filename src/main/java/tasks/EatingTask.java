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

    public EatingTask(Animal animal) {
        this.animal = animal;
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {
            try {
                if (!animal.isAlive())
                    Thread.currentThread().interrupt();
                eatingAction();
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void eatingAction() {

        Entity prey;
        List<Entity> food;
        Location location = animal.getLocation();
        if (animal instanceof Predator) {
            food = location.getIsland().getHerbivores(location);
        } else if (animal instanceof Herbivore) {
            food = location.getIsland().getFoodForHerbivores(location);
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
