package tasks;

import entity.Animal;
import java.util.concurrent.TimeUnit;

public class HungerTask implements Runnable{

    private final Animal animal;

    public HungerTask(Animal animal) {
        this.animal = animal;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                if (!animal.isAlive())
                    Thread.currentThread().interrupt();
                hungerAction();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void hungerAction() {
        animal.hunger();
    }
}