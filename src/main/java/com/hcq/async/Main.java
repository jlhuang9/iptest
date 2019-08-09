package com.hcq.async;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        System.out.println("--------------------------------------");
        System.out.println("方法1");
        test1();
        System.out.println("--------------------------------------");
        System.out.println("方法2");
        test2();
        System.out.println("--------------------------------------");
        System.out.println("方法3");
        test3();
        System.out.println("--------------------------------------");


    }

    public static void test1() {
        Model1 model1 = new Model1();
        System.out.println("开始！");
        new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("赋值！");
            model1.setValue("123");
        }).start();
        String value = model1.getValue();
        System.out.println(value);
    }

    public static void test2() {
        Model2 model2 = new Model2();
        new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            model2.setValue("123");
        }).start();
        new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            model2.setValue("234");
        }).start();
        System.out.println(model2.getValue());
    }

    public static void test3() {
        Model3 model3 = new Model3();
        new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            model3.setValue("123");
        }).start();
        new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            model3.setValue("234");
        }).start();
        System.out.println(model3.getValue());
    }
}
