package executors;

import entity.animals.Herbivore;
import entity.animals.Predator;
import org.island.Island;
import org.island.Location;
import tasks.EatingTask;
import tasks.GrowingTask;

import java.util.concurrent.*;

public class EntityActionExecutor {

    private final Island island;
    private final ExecutorService actingExecutor;

    public EntityActionExecutor(Island island) {
        this.island = island;
        this.actingExecutor = Executors.newCachedThreadPool();
    }

    public void startAnimalEatingTask() {
        for (int i = 0; i < island.getRows(); i++) {
            for (int j = 0; j < island.getCols(); j++) {
                Location location = island.getLocation(i, j);
                for (Predator predator : location.getPredators()) {
                    EatingTask eatingTask = new EatingTask(predator, location);
                    actingExecutor.submit(eatingTask);
                }
                for (Herbivore herbivore : location.getHerbivores()) {
                    EatingTask eatingTask = new EatingTask(herbivore, location);
                    actingExecutor.submit(eatingTask);
                }
            }
        }
    }

    public void startGrowingTask() {
        for (int i = 0; i < island.getRows(); i++) {
            for (int j = 0; j < island.getCols(); j++) {
                Location location = island.getLocation(i, j);
                GrowingTask growingTask = new GrowingTask(location);
                actingExecutor.submit(growingTask);
            }
        }
    }


    public void stopEntityTaskAction() {
        actingExecutor.shutdownNow();
    }
}
