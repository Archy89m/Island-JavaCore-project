package tasks;

import entity.Entity;
import org.island.Island;
import providers.EntityFactory;

import java.util.List;

public class GrowingTask implements Runnable{

    private final Island island;

    public GrowingTask(Island island) {
        this.island = island;
    }

    @Override
    public void run() {

        for (int i = 0; i < island.getRows(); i++) {
            for (int j = 0; j < island.getCols(); j++) {
                List<Entity> plants = EntityFactory.growPlants(1);
                for (Entity entity:plants)
                    entity.setLocation(island.getLocation(i, j));
                island.addEntities(plants);
            }
        }
    }

}
