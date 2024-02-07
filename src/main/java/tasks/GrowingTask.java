package tasks;

import entity.Entity;
import entity.Plant;
import org.island.Location;
import providers.EntityFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GrowingTask implements Runnable{

    private final Location location;

    public GrowingTask(Location location) {
        this.location = location;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                growingAction();
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void growingAction() {

        List<Entity> plants = new ArrayList<>();
        EntityFactory.addEntitiesToList(Plant.class, plants);

        location.addEntities(plants);
/*
        for (Entity plant:plants)
            location.addEntity(plant);
*/
    }
}
