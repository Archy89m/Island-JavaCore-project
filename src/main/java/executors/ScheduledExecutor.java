package executors;

import org.island.Island;
import tasks.BirthTask;
import tasks.ClearTask;
import tasks.StatisticsTask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutor {

    private final Island island;
    private final ScheduledExecutorService statisticsScheduler;
    private final ScheduledExecutorService clearingExecutor;
    private final ScheduledExecutorService birthScheduler;
    private final EntityActionExecutor entityActionExecutor;

    public ScheduledExecutor(Island island) {

        this.island = island;
        this.clearingExecutor = Executors.newScheduledThreadPool(1);
        this.statisticsScheduler = Executors.newScheduledThreadPool(1);
        this.birthScheduler = Executors.newScheduledThreadPool(4);
        this.entityActionExecutor = new EntityActionExecutor(island);
    }

    public void startStatisticsTask() {

        StatisticsTask statisticsTask = new StatisticsTask(island);
        statisticsScheduler.scheduleAtFixedRate(statisticsTask, 6, 3, TimeUnit.SECONDS);
    }

    public void startClearingTask() {

        ClearTask clearTask = new ClearTask(island);
        clearingExecutor.scheduleAtFixedRate(clearTask, 1, 5, TimeUnit.SECONDS);
    }

    public void startBirthTask() {

        BirthTask birthTask = new BirthTask(entityActionExecutor);
        birthScheduler.scheduleAtFixedRate(birthTask, 1, 10, TimeUnit.SECONDS);
    }

    public void stopTasks() {

        clearingExecutor.shutdownNow();
        statisticsScheduler.shutdownNow();
        birthScheduler.shutdownNow();
        entityActionExecutor.stopEntityTaskAction();
    }
}