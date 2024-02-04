package org.island;

import entity.Entity;
import providers.EntityFactory;

import java.util.ArrayList;
import java.util.List;

public class Location {

    private List<Entity> entities;

    public Location() {
        this.entities = new ArrayList<>();
        initializeEntities();
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public List<Entity> getEntities() {
        return entities;
    }

    private void initializeEntities() {
        entities.addAll(EntityFactory.createEntities());
    }
}
