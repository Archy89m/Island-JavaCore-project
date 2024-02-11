package providers;

import entity.Entity;
import entity.Plant;
import org.island.Island;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class EntityFactory {

    public static List<Entity> createEntities()  {

        List<Entity> entities = new ArrayList<>();

        try {
            PackageScanner scanner = new PackageScanner();
            List<Class<?>> classes = scanner.getClasses("entity");

            for (Class<?> clazz : classes)
                addEntitiesToList(clazz, entities);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return entities;
    }

    public static void addEntitiesToList(Class<?> clazz, List<Entity> entities) {

        if (!Modifier.isAbstract(clazz.getModifiers())) {
            try {
                Field field = clazz.getDeclaredField("maxOnCell");
                field.setAccessible(true);
                int value = (int) field.get(null);

                value = value / 100 + 1;

                Random random = new Random();
                int numberOfEntities = random.nextInt(value + 1);

                for (int i = 0; i < numberOfEntities; i++) {
                    Entity entity = (Entity) clazz.getDeclaredConstructor().newInstance();
                    entities.add(entity);
                }
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                     InvocationTargetException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

    public static Entity reproduceAnimals(Class<?> clazz)  {

        Entity entity = null;
        try {
            entity = (Entity) clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | NoSuchMethodException
                 | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    public static List<Entity> growPlants(int maxNumber) {

        List<Entity> plants = new ArrayList<>();
        int numberOfPlants = ThreadLocalRandom.current().nextInt(maxNumber + 1);

        for (int i = 0; i < numberOfPlants; i++) {
            Entity plant = new Plant();
            plants.add(plant);
            Island.increaseNumberOfBorn();
        }
        return plants;
    }
}
