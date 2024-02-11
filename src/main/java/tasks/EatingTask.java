package tasks;

import entity.Animal;
import entity.Entity;
import entity.animals.Herbivore;
import entity.animals.Predator;

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
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void eatingAction() {
        Entity prey = null;
        List<Entity> food = null;
        if (animal instanceof Predator) {
            food = animal.getLocation().getHerbivores();
        } else if (animal instanceof Herbivore) {
            food = animal.getLocation().getFoodForHerbivores();
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
