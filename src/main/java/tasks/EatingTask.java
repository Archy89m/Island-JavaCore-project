package tasks;

import entity.Animal;
import entity.Entity;
import entity.Plant;
import entity.animals.Herbivore;
import entity.animals.Predator;
import org.island.Location;

import java.util.List;
import java.util.Random;
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
        if (animal instanceof Predator) {
            List<Herbivore> herbivores = location.getHerbivores();
            if (!herbivores.isEmpty()) {
                prey = herbivores.get(ThreadLocalRandom.current().nextInt(herbivores.size()));
            } else {
                return;
            }
        } else if (animal instanceof Herbivore) {
            List<Plant> plants = location.getPlants();
            if (!plants.isEmpty()) {
                prey = plants.get(ThreadLocalRandom.current().nextInt(plants.size()));
            } else {
                return;
            }
        } else {
            return;
        }

        if (animal.eat(prey))
            this.location.removeEntity(prey);

    }
}
