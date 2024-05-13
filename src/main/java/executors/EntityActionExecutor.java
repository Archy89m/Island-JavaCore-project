package executors;

import entity.Animal;
import entity.Entity;
import org.island.Island;
import tasks.*;

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

        List<Entity> kids = island.getKids();

        if (kids.isEmpty())
            return;

        for (Entity entity:kids) {
            if (entity instanceof Animal animal) {
                actingExecutor.submit(new MovingTask(animal));
                actingExecutor.submit(new HungerTask(animal));
                actingExecutor.submit(new EatingTask(animal));
                actingExecutor.submit(new ReproduceTask(animal));
            }
        }

        island.addEntities(kids);
        island.removeKids(kids);
    }

    public void startGrowingTask() {

        GrowingTask growingTask = new GrowingTask(island);
        actingExecutor.submit(growingTask);
    }

    public void stopEntityTaskAction() {
        actingExecutor.shutdownNow();
    }
}