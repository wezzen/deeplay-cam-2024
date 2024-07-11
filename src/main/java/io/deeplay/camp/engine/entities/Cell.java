package io.deeplay.camp.engine.entities;

import java.util.Random;

public class Cell {
    private int x;
    private int y;

    Planet planet;
    boolean isPlanet;

    public Cell(int x, int y, boolean isPlanet) {
        Random random = new Random();
        this.isPlanet = isPlanet;
        if (isPlanet) {
            planet = new Planet(random.nextInt(10));
        }

        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Planet getPlanet() {
        return planet;
    }

    public boolean isPlanet() {
        return isPlanet;
    }
}
