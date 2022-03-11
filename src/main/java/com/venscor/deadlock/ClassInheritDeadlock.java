package com.venscor.deadlock;

import com.github.phantomthief.util.MoreReflection;


/**
 * @author wangyu07 <wangyu07@kuaishou.com>
 * Created on 2022-03-07
 */
public class ClassInheritDeadlock {

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> new Parent());
        //        Thread thread1 = new Thread(() -> new MoreReflection());

        thread1.setName("Test Thread1");
        thread1.start();


        Thread thread2 = new Thread(() -> new Child());
        //        Thread thread2 = new Thread(() -> new MoreReflection());
        thread2.setName("Test Thread2");
        thread2.start();

    }

    static class Parent {
        static final Parent parent = new Child();
    }

    static class Child extends Parent {

    }
}
