package com.fallon.springbootapp.deadlock;

public interface MonitorableRunnable extends Runnable {
    public Thread.State getThreadState();
    public String getThreadName();
}
