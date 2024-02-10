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
        List<Entity> plants = EntityFactory.growPlants(1);
        location.addEntities(plants);
    }

}
