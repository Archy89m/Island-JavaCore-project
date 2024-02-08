package org.island;

import executors.EntityActionExecutor;
import executors.ScheduledExecutor;
import java.time.LocalTime;

public class Island {

    private final int rows = 1;
    private final int cols = 1;
    private final Location[][] locations;
    private ScheduledExecutor scheduledExecutor = null;
    private EntityActionExecutor entityActionExecutor = null;

    public Island() {
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

        scheduledExecutor = new ScheduledExecutor(this);
        scheduledExecutor.startStatisticsTask();
        scheduledExecutor.startClearingTask();

        entityActionExecutor = new EntityActionExecutor(this);
        entityActionExecutor.startAnimalLivingTasks();
        entityActionExecutor.startGrowingTask();

    }

    public void stopSimulation() {
        scheduledExecutor.stopTasks();
        entityActionExecutor.stopEntityTaskAction();
    }
}
