package org.island;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;


public class App {
    public static void main(String[] args) {
        Island island = new Island();
        island.startSimulation();

        try {
            TimeUnit.SECONDS.sleep(60);
            System.out.println("Stopping the game " + LocalTime.now());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        island.stopSimulation();
    }
}