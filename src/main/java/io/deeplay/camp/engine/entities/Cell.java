package io.deeplay.camp.engine.entities;

/**
*Класс клетки поля
* */
public class Cell {
    private final int x;
    private final int y;

    private final Planet planet;

    public Cell(final int x,final int y, Planet planet) {
        this.planet= planet;
        this.x = x;
        this.y = y;
    }
    public Cell(final int x,final int y) {
        this.x = x;
        this.y = y;
        this.planet=null;
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

}
