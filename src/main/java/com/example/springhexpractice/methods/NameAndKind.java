package com.example.springhexpractice.methods;

public enum NameAndKind {
    //A:諾亞方舟號,B:霍格華茲號

    A("諾亞方舟號", "A"), B("霍格華茲號", "B");

    private String name;
    private String kind;

    NameAndKind(String name, String kind) {
        this.name = name;
        this.kind = kind;
    }

    public String getName(String kind) {
        for (NameAndKind train : NameAndKind.values()) {
            if (train.kind.equals(kind))
                return name;
        }
        return null;
    }

    public String getKind(String name) {
        for (NameAndKind train : NameAndKind.values()) {
            if (train.name.equals(name))
                return kind;
        }
        return null;
    }
}
