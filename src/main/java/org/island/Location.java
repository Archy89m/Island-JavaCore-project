package org.island;

import entity.Entity;
import providers.EntityFactory;

import java.util.List;

public class Location {
    private final Island ISLAND;
    private final int I;
    private final int J;
    public Location(Island island, int i, int j) {
        this.ISLAND = island;
        this.I = i;
        this.J = j;
        initializeEntities();
    }
    public Island getIsland() {
        return ISLAND;
    }
    public int getI() {
        return I;
    }
    public int getJ() {
        return J;
    }
    private void initializeEntities() {
        List<Entity> newEntities = EntityFactory.createEntities();
        for (Entity newEntity:newEntities)
            newEntity.setLocation(this);
        ISLAND.addKids(newEntities);
    }
}