package org.island;

import entity.Animal;
import entity.Entity;
import entity.Plant;
import entity.animals.Herbivore;
import executors.ScheduledExecutor;
import providers.SettingsProvider;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Island {
    private final int ROWS = 10;
    private final int COLS = 20;
    private final Location[][] locations;
    private final List<Entity> kids;
    private final List<Entity> entities;
    private ScheduledExecutor scheduledExecutor = null;
    private static final AtomicInteger numberOfBorn = new AtomicInteger();
    private static final AtomicInteger numberOfDeaths = new AtomicInteger();
    Island() {
        this.locations = new Location[ROWS][COLS];
        this.entities = new ArrayList<>();
        this.kids = new ArrayList<>();
        initializeLocations();
        System.out.println("Island created - " + LocalTime.now());
        System.out.println();
    }
    public int getRows() {
        return ROWS;
    }
    public int getCols() {
        return COLS;
    }
    public static void increaseNumberOfBorn() {
        numberOfBorn.getAndIncrement();
    }
    public static void increaseNumberOfDeaths() {
        numberOfDeaths.getAndIncrement();
    }
    public static int getBorn() {
        return numberOfBorn.get();
    }
    public static int getDead() {
        return numberOfDeaths.get();
    }
    private void initializeLocations() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                locations[i][j] = new Location(this, i, j);
            }
        }
    }
    public synchronized void addEntities(List<Entity> entityList) {
        entities.addAll(entityList);
    }
    public Location getLocation(int row, int col) {
        return locations[row][col];
    }
    void startSimulation() {
        scheduledExecutor = new ScheduledExecutor(this);
        scheduledExecutor.startBirthTask();
        scheduledExecutor.startClearingTask();
        scheduledExecutor.startStatisticsTask();
    }
    void stopSimulation() {
        scheduledExecutor.stopTasks();
    }
    synchronized void addKids(List<Entity> listKids) {
        kids.addAll(listKids);
    }
    public synchronized void removeKids(List<Entity> listEntities) {
        kids.removeAll(listEntities);
    }
    public synchronized List<Entity> getEntities() {
        return new ArrayList<>(entities);
    }
    public synchronized List<Entity> getKids() {
        return new ArrayList<>(kids);
    }
    public List<Entity> getDeadEntities() {
        List<Entity> copy = new ArrayList<>(entities);
        return copy.stream()
              .filter(entity -> !entity.isAlive())
              .toList();
    }
    public List<Entity> getHerbivores(Location location) {
        List<Entity> copy = new ArrayList<>(entities);
        return copy.stream()
                .filter(entity -> entity.getLocation() == location)
                .filter(entity -> entity instanceof Herbivore)
                .filter(Entity::isAlive)
                .toList();
    }
    public List<Entity> getFoodForHerbivores(Location location) {
         List<Entity> copy = new ArrayList<>(entities);
         List<Entity> plants = copy.stream()
                 .filter(entity -> entity.getLocation() == location)
                 .filter(entity -> entity instanceof Plant)
                 .filter(Entity::isAlive)
                 .toList();

        List<String> classNames = SettingsProvider.getListHerbivoreAsFoodForHerbivores();
        copy = new ArrayList<>(entities);
        List<Entity> herbivoreAsFoodForHerbivores = copy.stream()
                .filter(entity -> entity.getLocation() == location)
                .filter(entity -> entity instanceof Herbivore)
                .filter(Entity::isAlive)
                .filter(herbivore -> classNames.contains(herbivore.getClass().getSimpleName()))
                .toList();

        return Stream.concat(plants.stream(), herbivoreAsFoodForHerbivores.stream())
              .toList();
    }
    public List<Entity> getAnimalOfClass(Class<? extends Animal> animalClass, Location location) {
        List<Entity> copy = new ArrayList<>(entities);
        return copy.stream()
                .filter(entity -> entity.getLocation() == location)
                .filter(animalClass::isInstance)
                .filter(Entity::isAlive)
                .toList();
    }
    public synchronized void addKid(Entity entity) {
        kids.add(entity);
    }
    public synchronized void removeEntities(List<Entity> listEntities) {
        entities.removeAll(listEntities);
    }
}