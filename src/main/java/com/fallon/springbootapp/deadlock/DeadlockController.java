package com.fallon.springbootapp.deadlock;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Detects thread deadlock
 */
@RestController
public class DeadlockController {

    /**
     * Creates two threads that will deadlock and detects the deadlock
     *
     * @return Returns Result object representing success or failure
     */
        @RequestMapping("/deadlock")
        public Result detectDeadlock() {
            String str1 = "String1";
            String str2 = "String2";

            ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
            executor.setCorePoolSize(4);
            executor.setMaxPoolSize(4);
            executor.setThreadNamePrefix("default_task_executor_thread");
            executor.initialize();

            MonitorableThreadPoolExecutor monitorableExecutor = new MonitorableThreadPoolExecutor(executor);
            monitorableExecutor.execute(new StringPrinter("Printer1", str1, str2));
            monitorableExecutor.execute(new StringPrinter("Printer2", str2, str1));

            return new Result(monitorableExecutor.isDeadlocked());
        }

    }

/**
 * Encapsulates a boolean representing success or failure
 */
class Result {
        private boolean success;

    /**
     * Constructor initializing success or failure.
     * 
     * @param success boolean representing whether or not the operation was successful
     */
    public Result(boolean success) {
            this.success = success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public boolean getSuccess() {
            return success;
        }
    }


