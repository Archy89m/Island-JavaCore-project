package org.island;

import entity.Entity;

import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Island {

    private final int rows = 20;
    private final int cols = 100;
    private Location[][] locations;
    private ScheduledExecutorService statisticsScheduler;

    public Island() {
        System.out.println("Start game - " + LocalTime.now());
        this.locations = new Location[rows][cols];
        initializeLocations();
        System.out.println("Island created - " + LocalTime.now());
        System.out.println();
        this.statisticsScheduler = Executors.newScheduledThreadPool(1);
    }

    private void initializeLocations() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                locations[i][j] = new Location();
            }
        }
    }

    public Location getLocation(int row, int col) {
        return locations[row][col];
    }

    public void displayStatistics() {

        Map<Class<?>, Integer> plantsCountMap = new HashMap<>();
        Map<Class<?>, Integer> herbivoresCountMap = new HashMap<>();
        Map<Class<?>, Integer> predatorsCountMap = new HashMap<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Location location = locations[i][j];
                for (Entity entity : location.getEntities()) {
                    Class<?> clazz = entity.getClass();
                    if (clazz.getName().contains("Plant")) {
                        plantsCountMap.put(clazz, plantsCountMap.getOrDefault(clazz, 0) + 1);
                    } else if (clazz.getName().contains("herbivores")) {
                        herbivoresCountMap.put(clazz, herbivoresCountMap.getOrDefault(clazz, 0) + 1);
                    } else if (clazz.getName().contains("predators")) {
                        predatorsCountMap.put(clazz, predatorsCountMap.getOrDefault(clazz, 0) + 1);
                    }
                }
            }
        }

        System.out.println("Statistics " + LocalTime.now());
        System.out.print("Plants: ");
        displayEntities(plantsCountMap);
        System.out.print("Herbivores: ");
        displayEntities(herbivoresCountMap);
        System.out.print("Predators: ");
        displayEntities(predatorsCountMap);
        System.out.println();
    }

    public void displayEntities(Map<Class<?>, Integer> entities) {

        for (Map.Entry<Class<?>, Integer> entry : entities.entrySet()) {
            try {
                Field field = entry.getKey().getDeclaredField("emoji");
                field.setAccessible(true);
                String emoji = (String) field.get(null);
                NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
                System.out.print(emoji + " " + numberFormat.format(entry.getValue()) + "|");
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println();
    }

    public void startSimulation() {
        statisticsScheduler.scheduleAtFixedRate(this::displayStatistics, 6, 6, TimeUnit.SECONDS);
    }

    public void stopSimulation() {
        statisticsScheduler.shutdown();
    }
}
