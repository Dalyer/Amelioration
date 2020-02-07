package com.example.david.amelioration;


class Exercise {
    private String name;
    private String description;
    private int restTimeMs;

    Exercise(String name, String description, int restTimeMs) {
        this.name = name;
        this.description = description;
        this.restTimeMs = restTimeMs;
    }
    // TODO finish implementing this class, adding stats, etc

    // Getter functions
    public String getName() {
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getRestTime() {
        return restTimeMs;
    }
}
