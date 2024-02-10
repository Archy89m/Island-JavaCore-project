package executors;

import entity.Animal;
import entity.Entity;
import org.island.Island;
import org.island.Location;
import tasks.EatingTask;
import tasks.GrowingTask;
import tasks.HungerTask;
import tasks.ReproduceTask;

import java.util.List;
import java.util.concurrent.*;

public class EntityActionExecutor {

    private final Island island;
    private final ExecutorService actingExecutor;

    public EntityActionExecutor(Island island) {
        this.island = island;
        this.actingExecutor = Executors.newCachedThreadPool();
    }

    public void startAnimalLivingTasks() {
        for (int i = 0; i < island.getRows(); i++) {
            for (int j = 0; j < island.getCols(); j++) {
                Location location = island.getLocation(i, j);
                List<Entity> kids = location.getKids();
                if (!kids.isEmpty()) {
                    for (Entity entity : kids) {
                        if (entity instanceof Animal animal) {
                            EatingTask eatingTask = new EatingTask((Animal)entity, location);
                            ReproduceTask reproduceTask = new ReproduceTask(animal, location);
                            HungerTask hungerTask = new HungerTask(animal);
                            actingExecutor.submit(eatingTask);
                            actingExecutor.submit(reproduceTask);
                            actingExecutor.submit(hungerTask);
                        }
                    }
                    location.addEntities(kids);
                    location.removeKids();
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
