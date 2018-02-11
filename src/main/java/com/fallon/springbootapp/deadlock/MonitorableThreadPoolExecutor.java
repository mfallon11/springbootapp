package com.fallon.springbootapp.deadlock;

import com.fallon.springbootapp.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

import java.util.ArrayList;
import java.util.List;

public class MonitorableThreadPoolExecutor implements SuccessCallback<MonitorableRunnable>, FailureCallback  {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    private ThreadPoolTaskExecutor taskExecutor;
    private List<MonitorableRunnable> taskList = new ArrayList<>();

    public MonitorableThreadPoolExecutor(ThreadPoolTaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public void execute(MonitorableRunnable task) {
        taskList.add(task);
        ListenableFuture future = taskExecutor.submitListenable(task);
        future.addCallback(this, this);
    }

    @Override
    public void onSuccess(MonitorableRunnable task) {
        taskList.remove(task);
    }

    @Override
    public void onFailure(Throwable ex) {}

    public List<MonitorableRunnable> getTasks() {
        return taskList;
    }

    // This should run in a thread and continuously monitor
    // The deadlock detection also needs much improvement
    public boolean isDeadlocked() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) { }

        int deadlocked = 0;
        for(MonitorableRunnable task : taskList) {
            if(task.getThreadState() == Thread.State.BLOCKED) {
                deadlocked++;
            }
        }

        if(deadlocked > 1) {
            return true;
        }

        return false;
    }
}
