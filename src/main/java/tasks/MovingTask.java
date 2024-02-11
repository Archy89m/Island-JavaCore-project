package tasks;

import entity.Animal;

import java.util.concurrent.TimeUnit;

public class MovingTask implements Runnable{

    private final Animal animal;

    public MovingTask(Animal animal) {
        this.animal = animal;
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {
            try {
                if (!animal.isAlive())
                    Thread.currentThread().interrupt();
                animal.move();
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
