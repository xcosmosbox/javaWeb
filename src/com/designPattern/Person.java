package com.designPattern;

public abstract class Person {
    public final void day(){
        wakeUp();
        eatBreakfast();
        doSome();
        eatDinner();
        washing();
        sleep();
    }

    private void sleep() {
        System.out.println("sleep");
    }

    private void washing() {
        System.out.println("washing");

    }

    private void eatDinner() {
        System.out.println("dinner");

    }

    protected abstract void doSome();

    private void eatBreakfast() {
        System.out.println("breakfast");

    }

    private void wakeUp() {
        System.out.println("wakeUp");

    }

}
