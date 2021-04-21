package facebook.lead.push.by.pageid.global;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TaskManager {
    private int threadCount;
    private ExecutorService executorService;

    public TaskManager(int threadCount){
        this.threadCount = threadCount;
        this.executorService = Executors.newFixedThreadPool(threadCount);
    }

    public void waitTillQueueIsFreeAndAddTask(Runnable runnable){
        while (getQueueSize() >= threadCount){
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        addTask(runnable);
    }

    private void addTask(Runnable runnable) {
        this.executorService.submit(runnable);
    }

    private int getQueueSize() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor)(executorService);
        return executor.getQueue().size();
    }

}
