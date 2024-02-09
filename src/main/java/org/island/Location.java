package org.island;

import entity.Animal;
import entity.Entity;
import entity.Plant;
import entity.animals.Herbivore;
import entity.animals.Predator;
import providers.EntityFactory;
import providers.SettingsProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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

    public void removeEntities(List<Entity> listEntities) {
        synchronized (entities) {
            entities.removeAll(listEntities);
        }
    }

    public List<Entity> getEntities() {
        return new ArrayList<>(entities);
    }

    private void initializeEntities() {
        entities.addAll(EntityFactory.createEntities());
    }

    public List<Animal> getAnimals() {
        List<Entity> copy = new ArrayList<>(entities);
        return copy.stream()
                .filter(entity -> entity instanceof Animal)
                .map(entity -> (Animal) entity)
                .filter(Animal::isAlive)
                .toList();
    }

    public List<Entity> getAnimalOfClass(Class<? extends Animal> animalClass) {
        List<Entity> copy = new ArrayList<>(entities);
        return copy.stream()
                .filter(animalClass::isInstance)
                .filter(Entity::isAlive)
                .toList();
    }

    public List<Entity> getHerbivores() {
        List<Entity> copy = new ArrayList<>(entities);
        return copy.stream()
                .filter(entity -> entity instanceof Herbivore)
                .filter(Entity::isAlive)
                .toList();
    }

    public List<Herbivore> getHerbivoresAsFoodForHerbivores() {
        List<String> classNames = SettingsProvider.getListHerbivoreAsFoodForHerbivores();
        List<Entity> copy = new ArrayList<>(entities);
        return copy.stream()
                .filter(entity -> entity instanceof Herbivore)
                .map(entity -> (Herbivore) entity)
                .filter(Herbivore::isAlive)
                .filter(herbivore -> classNames.contains(herbivore.getClass().getSimpleName()))
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

    public List<Entity> getFoodForHerbivores() {

        List<Entity> copy = new ArrayList<>(entities);
        List<Entity> plants = copy.stream()
                .filter(entity -> entity instanceof Plant)
                .filter(Entity::isAlive)
                .toList();

        List<String> classNames = SettingsProvider.getListHerbivoreAsFoodForHerbivores();
        copy = new ArrayList<>(entities);
        List<Entity> herbivoreAsFoodForHerbivores = copy.stream()
                .filter(entity -> entity instanceof Herbivore)
                .filter(Entity::isAlive)
                .filter(herbivore -> classNames.contains(herbivore.getClass().getSimpleName()))
                .toList();

        return Stream.concat(plants.stream(), herbivoreAsFoodForHerbivores.stream())
                .toList();
    }

    public List<Entity> getDeadEntities() {
        List<Entity> copy = new ArrayList<>(entities);
        return copy.stream()
                .filter(entity -> !entity.isAlive())
                .toList();
    }
}
