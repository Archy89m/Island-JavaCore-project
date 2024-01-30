package org.example;

import entity.animals.herbivores.Horse;
import entity.animals.predators.Wolf;
import providers.EatingProbabilityProvider;

public class Main {
    public static void main(String[] args) {

        Object wolf = new Wolf();
        Object horse = new Horse();

        System.out.println(EatingProbabilityProvider.getProbability(wolf.getClass().getSimpleName(), horse.getClass().getSimpleName()));

    }
}