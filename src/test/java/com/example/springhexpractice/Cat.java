package com.example.springhexpractice;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Cat {
    private int name;
    private int age;
    private String description;

    public int getName() {
        return name;
    }

    public void setName(int name) {
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
