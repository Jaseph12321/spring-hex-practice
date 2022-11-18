package com.example.springhexpractice.infra.utils;

public enum NameAndKind {
    //A:諾亞方舟號,B:霍格華茲號

    A("諾亞方舟號", "A",43), B("霍格華茲號", "B");

    private  String name;
    private  String kind;

    private int age;

    NameAndKind(String name, String kind, int age) {
        this.name = name;
        this.kind = kind;
        this.age = age;
    }

    NameAndKind(String name, String kind) {
        this.name = name;
        this.kind = kind;
    }

    public static String getName(String kind) {
        for (NameAndKind train : NameAndKind.values()) {
            if (train.kind.equals(kind))
                return train.name;
        }
        return null;
    }

    public static String getKind(String name) {
        for (NameAndKind train : NameAndKind.values()) {
            if (train.name.equals(name))
                return train.kind;
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public String getKind() {
        return kind;
    }

    public int getAge() {
        return age;
    }
}
