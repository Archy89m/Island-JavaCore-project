package tasks;

import entity.Entity;
import org.island.Island;

import java.util.List;


public class ClearTask implements Runnable{

    private final Island island;

    public ClearTask(Island island) {
        this.island = island;
    }

    @Override
    public void run() {

        List<Entity> deadEntities = island.getDeadEntities();
        island.removeEntities(deadEntities);
    }
}
