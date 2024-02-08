package executors;

import org.island.Island;
import tasks.ClearTask;
import tasks.StatisticsTask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutor {

    private final Island island;
    private final ScheduledExecutorService statisticsScheduler;

    private final ScheduledExecutorService clearingExecutor;

    public ScheduledExecutor(Island island) {
        this.island = island;
        this.clearingExecutor = Executors.newScheduledThreadPool(1);
        this.statisticsScheduler = Executors.newScheduledThreadPool(1);
    }

    public void startStatisticsTask() {
        StatisticsTask statisticsTask = new StatisticsTask(island);
        statisticsScheduler.scheduleAtFixedRate(statisticsTask, 1, 6, TimeUnit.SECONDS);
    }

    public void startClearingTask() {
        for (int i = 0; i < island.getRows(); i++) {
            for (int j = 0; j < island.getCols(); j++) {
                ClearTask clearTask = new ClearTask(island.getLocation(i,j));
                clearingExecutor.scheduleAtFixedRate(clearTask, 1, 1, TimeUnit.SECONDS);
            }
        }
    }

    public void stopTasks() {
        clearingExecutor.shutdownNow();
        statisticsScheduler.shutdownNow();
    }


}
