package io.deeplay.camp.engine.entities;

import java.util.Random;

public class Cell {
    private int x;
    private int y;

    Planet planet;
    boolean isPlanet;

    public void generatePlanet() {
        Random random = new Random();
        isPlanet = true;
        planet = new Planet(random.nextInt(10));
    }
}
