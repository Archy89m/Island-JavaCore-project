package org.island;


import providers.AnimalFactory;

public class App {
    public static void main(String[] args) {

        AnimalFactory.createAnimalsFromPackage("entity.animals.herbivores");
        //List<Animal> animals = AnimalFactory.createAnimals();

    }
}