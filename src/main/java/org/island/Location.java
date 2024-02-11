package org.island;

import entity.Entity;
import providers.EntityFactory;

import java.util.List;

public class Location {

    private final Island island;
    private final int i;
    private final int j;

    public Location(Island island, int i, int j) {

        this.island = island;
        this.i = i;
        this.j = j;

        initializeEntities();
    }

    public Island getIsland() {
        return island;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    private void initializeEntities() {

        List<Entity> newEntities = EntityFactory.createEntities();

        for (Entity newEntity:newEntities)
            newEntity.setLocation(this);

        island.addKids(newEntities);
    }
}
