package org.island;

import java.time.LocalTime;


public class App {
    public static void main(String[] args) {

        Island island = new Island();
        island.startSimulation();

        try {
            Thread.sleep(1000 * 30);
            System.out.println("Stopping the game " + LocalTime.now());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        island.stopSimulation();
    }

}

