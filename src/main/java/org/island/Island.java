package org.island;

import executors.EntityActionExecutor;
import executors.StatisticsExecutor;
import java.time.LocalTime;

public class Island {

    private final int rows = 5;
    private final int cols = 2;
    private final Location[][] locations;
    private StatisticsExecutor statisticsExecutor = null;
    private EntityActionExecutor entityActionExecutor = null;

    public Island() {
        System.out.println("Start game - " + LocalTime.now());
        this.locations = new Location[rows][cols];
        initializeLocations();
        System.out.println("Island created - " + LocalTime.now());
        System.out.println();
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Location[][] getLocations() {
        return locations;
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

    public void startSimulation() {

        statisticsExecutor = new StatisticsExecutor(this);
        statisticsExecutor.startStatisticsTask();

        entityActionExecutor = new EntityActionExecutor(this);
        entityActionExecutor.startAnimalEatingTask();
        entityActionExecutor.startGrowingTask();

    }

    public void stopSimulation() {
        statisticsExecutor.stopStatisticsTask();
        entityActionExecutor.stopEntityTaskAction();
    }
}
