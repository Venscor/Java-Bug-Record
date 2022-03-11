package com.venscor.deadlock;

/**
 * @author wangyu07 <wangyu07@kuaishou.com>
 * Created on 2022-03-02
 */
public class Sub extends Super {
    public Sub() {
        System.out.println(Thread.currentThread() + "In Sub constructor");
    }
}
