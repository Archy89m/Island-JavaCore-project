package executors;

import org.island.Island;
import tasks.StatisticsTask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class StatisticsExecutor {

    private final Island island;
    private final ScheduledExecutorService statisticsScheduler;

    public StatisticsExecutor(Island island) {
        this.island = island;
        this.statisticsScheduler = Executors.newScheduledThreadPool(1);
    }

    public void startStatisticsTask() {
        StatisticsTask statisticsTask = new StatisticsTask(island);
        statisticsScheduler.scheduleAtFixedRate(statisticsTask, 1, 6, TimeUnit.SECONDS);
    }

    public void stopStatisticsTask() {
        statisticsScheduler.shutdownNow();
    }
}
