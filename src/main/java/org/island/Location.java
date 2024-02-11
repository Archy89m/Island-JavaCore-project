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

    private final Island island;
    private final List<Entity> entities;
    private final List<Entity> kids;
    private final int i;
    private final int j;

    public Location(Island island, int i, int j) {
        this.island = island;
        this.i = i;
        this.j = j;
        this.entities = new ArrayList<>();
        this.kids = new ArrayList<>();
        initializeEntities();
    }

    public synchronized void addEntity(Entity entity) {
        entities.add(entity);
    }

    public synchronized void addEntities(List<Entity> entityList) {
        entities.addAll(entityList);
    }

    public synchronized void addKid(Entity entity) {
        kids.add(entity);
    }

    public List<Entity> getKids() {
        List<Entity> copy = new ArrayList<>(kids);
        return copy.stream()
                .toList();
    }

    public synchronized void removeKids(List<Entity> listEntities) {
        kids.removeAll(listEntities);
    }

    public synchronized void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public synchronized void removeEntities(List<Entity> listEntities) {
        entities.removeAll(listEntities);
    }

    public List<Entity> getEntities() {
        return new ArrayList<>(entities);
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

        kids.addAll(newEntities);
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
