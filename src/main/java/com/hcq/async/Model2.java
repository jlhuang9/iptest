package com.hcq.async;

/**
 * 同步方法二
 */
public class Model2 {
    //使用volatile 防止线程缓存
    private volatile String value;

    private volatile boolean isOk = false;

    public synchronized String getValue() {
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value;
    }

    public synchronized void setValue(String value) {
        if (this.isOk == true) {
            System.out.println("已经赋值[" + this.value + "]，赋值【" + value + "]失败！");
        } else {
            //先赋值，再同步
            this.isOk = true;
            this.value = value;
            System.out.println("赋值[" + this.value+"]");
            this.notify();
        }

    }
}
