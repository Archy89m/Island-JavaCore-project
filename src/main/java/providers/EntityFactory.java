package providers;

import entity.Entity;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;


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


}
