package tasks;

import entity.Entity;
import org.island.Location;

import java.util.List;


public class ClearTask implements Runnable{

    private final Location location;

    public ClearTask(Location location) {
        this.location = location;
    }

    @Override
    public void run() {
        List<Entity> deadEntities = location.getDeadEntities();
        location.removeEntities(deadEntities);
    }
}
