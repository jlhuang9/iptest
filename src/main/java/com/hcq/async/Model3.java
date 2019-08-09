package com.hcq.async;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class Model3 {
    //使用volatile 防止线程缓存
    private volatile String value;
    private AtomicBoolean isOk = new AtomicBoolean(false);

    private CountDownLatch lock = new CountDownLatch(1);

    public String getValue() {
        try {
            lock.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value;
    }

    public void setValue(String value) {
        //幂等操作
        if (isOk.compareAndSet(false, true)) {
            //先赋值，再同步
            this.value = value;
            System.out.println("赋值[" + this.value+"]");
            this.lock.countDown();
        } else {
            System.out.println("已经赋值[" + this.value + "]，赋值【" + value + "]失败！");
        }
    }
}
