package com.hcq.async;

/**
 * 同步方法一
 */
public class Model1 {
    //使用volatile 防止线程缓存
    private volatile String value;

    public synchronized String getValue() {
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value;
    }

    public synchronized void setValue(String value) {
        //先赋值，再同步
        this.value = value;
        this.notify();
    }
}
