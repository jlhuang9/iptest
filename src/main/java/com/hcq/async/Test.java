package com.hcq.async;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        new Thread(() -> {
            test.setValue("123");
        }).start();
        System.out.println(test.getValue());
    }

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
