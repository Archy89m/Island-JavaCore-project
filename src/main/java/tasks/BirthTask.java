package tasks;

import executors.EntityActionExecutor;

public class BirthTask implements Runnable{

    private final EntityActionExecutor entityActionExecutor;

    public BirthTask(EntityActionExecutor entityActionExecutor) {
        this.entityActionExecutor = entityActionExecutor;
    }
    @Override
    public void run() {
        entityActionExecutor.startAnimalLivingTasks();
        entityActionExecutor.startGrowingTask();
    }
}
