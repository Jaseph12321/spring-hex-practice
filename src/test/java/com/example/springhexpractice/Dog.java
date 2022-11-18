package com.example.springhexpractice;

public class Dog {
    private String name;
    private int  age;
    private String description;
    private Cat cat;

    public Dog(Cat cat) {
        this.cat = cat;
    }
    public Dog(String name, int age, String description) {
        this.name = name;
        this.age = age;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
