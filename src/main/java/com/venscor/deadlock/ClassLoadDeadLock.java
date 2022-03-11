package com.venscor.deadlock;

/**
 * @author Venscor
 * Created on 2022-03-02
 */
public class ClassLoadDeadLock {

    public static void main(String[] args) {
        if (args.length != 1) {
            throw new RuntimeException("Usage: ClassDeadlockTest delay_millis (try 500 and 1500)");
        }
        int delayMillis = Integer.valueOf(args[0]);
        System.out.println("Delay = " + delayMillis + " millis");


        //（1）A线程创建Super类
        //（2）A线程执行getHelloString，打印Sleeping语句
        //（3）A线程执行Super中的getHelloString方法等待1000ms
        //（4）主线程等到500时间到
        //（5）主线程打印Creating sub ...
        //（6）主线程试图加载Sub类
        //（7）A线程getHelloString等待时间到
        //（8）A线程打印Creating new Sub()
        //（9）A线程试图加载Sub类
        //（10）可能是：主线程加载Sub类还没完成的时候，A线程开始加载Sub类导致？并发加载？？？？

        Thread thread = new Thread(() -> System.out.println(Thread.currentThread() + "Created super: " + new Super()));
        thread.setName("Thread A");
        thread.start();

        try {
            Thread.sleep(delayMillis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Thread.currentThread() + "Creating sub...");
        System.out.println(Thread.currentThread() + "Created sub: " + new Sub());
    }


}
