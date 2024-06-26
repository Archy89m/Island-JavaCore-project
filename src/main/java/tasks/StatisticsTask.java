package tasks;

import entity.Entity;
import org.island.Island;

import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;


public class StatisticsTask implements Runnable{

    private final Island island;

    public StatisticsTask(Island island) {
        this.island = island;
    }

    @Override
    public void run() {
        displayStatistics();
    }

    private void displayStatistics() {

        Map<Class<?>, Integer> plantsCountMap = new HashMap<>();
        Map<Class<?>, Integer> herbivoresCountMap = new HashMap<>();
        Map<Class<?>, Integer> predatorsCountMap = new HashMap<>();

        List<Entity> entities = Stream.concat(island.getEntities().stream(), island.getKids().stream()).toList();

        for (Entity entity:entities) {
            Class<?> clazz = entity.getClass();
            if (clazz.getName().contains("Plant")) {
                plantsCountMap.put(clazz, plantsCountMap.getOrDefault(clazz, 0) + 1);
            } else if (clazz.getName().contains("herbivores")) {
                herbivoresCountMap.put(clazz, herbivoresCountMap.getOrDefault(clazz, 0) + 1);
            } else if (clazz.getName().contains("predators")) {
                predatorsCountMap.put(clazz, predatorsCountMap.getOrDefault(clazz, 0) + 1);
            }
        }

        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println("Statistics " + currentTime.format(formatter));

        displayEntities(plantsCountMap, "Plants");
        displayEntities(herbivoresCountMap, "Herbivores");
        displayEntities(predatorsCountMap, "Predators");

        System.out.println("Born - " + Island.getBorn() + ", dead - " + Island.getDead());
        System.out.println("Number of threads - " + Thread.activeCount());
        System.out.println();
    }

    public void displayEntities(Map<Class<?>, Integer> entities, String type) {

        System.out.print(type + ": ");

        for (Map.Entry<Class<?>, Integer> entry : entities.entrySet()) {
            try {
                Field field = entry.getKey().getDeclaredField("EMOJI");
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
}