package com.venscor.deadlock;

/**
 * @author Venscor
 * Created on 2022-03-02
 */
public class Super {

    public static final String hello = getHelloString();

    private static String getHelloString() {
        int sleepTime = 1000;
        System.out.println(Thread.currentThread()+ "Sleeping in Super static init for " + sleepTime + " millis");
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread() + "Creating new Sub()");
        //依赖子类
        return "Sub Instance = " + new Sub();
    }
}
