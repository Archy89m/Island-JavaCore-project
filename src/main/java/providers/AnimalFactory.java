package providers;

import entity.Animal;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AnimalFactory {

    public static void createAnimalsFromPackage(String packageName) {

        try {
            PackageScanner scanner = new PackageScanner();
            List<Class<?>> classes = scanner.getClasses(packageName);

            for (Class<?> clazz : classes) {
                try {
                    Animal animal = (Animal) clazz.getDeclaredConstructor().newInstance();
                    System.out.println("Created animal: " + animal.getClass().getSimpleName());
                } catch (InstantiationException | IllegalAccessException |
                         NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
