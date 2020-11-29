package com.company;

public class Person {
    private String name;
    private int delay;

    public Person() {
    }

    public Person(String name,int delay) {
        this.name = name;
        this.delay = delay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

}
