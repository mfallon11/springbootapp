package com.fallon.springbootapp.deadlock;

public class StringPrinter implements MonitorableRunnable {
    private static int TIMEOUT = 100;

    private String name;
    private String str1;
    private String str2;

    private Thread current;

    public StringPrinter(String name, String str1, String str2) {
        this.str1 = str1;
        this.str2 = str2;
        this.name = name;
    }

    @Override
    public void run() {
        current = Thread.currentThread();

        boolean running = true;

        while(running){
            synchronized(str1){
                try { Thread.sleep(TIMEOUT); } catch(InterruptedException e) { System.out.println("trd1 interrupted"); }
                synchronized(str2){
                    System.out.println(str1 + str2);
                    running = false;
                }
            }
        }
    }

    public Thread.State getThreadState() {
        return current.getState();
    }

    public String getThreadName() {
        return current.getName();
    }
}

