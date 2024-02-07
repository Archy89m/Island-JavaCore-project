package org.island;

import entity.Entity;
import entity.Plant;
import entity.animals.Herbivore;
import entity.animals.Predator;
import providers.EntityFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Location {

    private List<Entity> entities;

    public Location() {
        this.entities = new ArrayList<>();
        initializeEntities();
    }

    public void addEntity(Entity entity) {
        synchronized (entities) {
            entities.add(entity);
        }
    }

    public void addEntities(List<Entity> listEntities) {
        synchronized (entities) {
            entities.addAll(listEntities);
        }
    }

    public void removeEntity(Entity entity) {
        synchronized (entities) {
            entities.remove(entity);
        }
    }

    public List<Entity> getEntities() {
        return new ArrayList<>(entities);
    }

    private void initializeEntities() {
        entities.addAll(EntityFactory.createEntities());
    }

    public List<Herbivore> getHerbivores() {
        List<Entity> copy = new ArrayList<>(entities);
        return copy.stream()
                .filter(entity -> entity instanceof Herbivore)
                .map(entity -> (Herbivore) entity)
                .filter(Herbivore::isAlive)
                .toList();
    }

    public List<Predator> getPredators() {
        List<Entity> copy = new ArrayList<>(entities);
        return copy.stream()
                .filter(entity -> entity instanceof Predator)
                .map(entity -> (Predator) entity)
                .filter(Predator::isAlive)
                .toList();
    }

    public List<Plant> getPlants() {
        List<Entity> copy = new ArrayList<>(entities);
        return copy.stream()
                .filter(entity -> entity instanceof Plant)
                .map(entity -> (Plant) entity)
                .filter(Plant::isAlive)
                .toList();
    }
}
