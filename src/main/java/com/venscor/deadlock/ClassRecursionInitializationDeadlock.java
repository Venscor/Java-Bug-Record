package com.venscor.deadlock;


import com.sun.tools.attach.VirtualMachine;

import sun.launcher.LauncherHelper;

/**
 * @author wangyu07 <wangyu07@kuaishou.com>
 * Created on 2022-03-07
 */
public class ClassRecursionInitializationDeadlock {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.getProperty("java.version"));

        Thread t = new Thread(() -> testInitializationDeadlock(0));
        t.setName("Test Thread");
        t.start();
        testInitializationDeadlock(1);
    }

    public static void testInitializationDeadlock(int tag) {
        if (tag == 0) {
            new A();
        } else {
            new B();
        }
    }

    static class A {
        static final B b = new B();
    }

    static class B {
        static final A a = new A();
    }
}
