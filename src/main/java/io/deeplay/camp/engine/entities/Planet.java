package io.deeplay.camp.engine.entities;

/**
 * Класс планеты
 */
public class Planet {
    /**
     * Плнета имеет:
     * 1) Очки за захват пленеты
     * 2) Владельца, если он есть
     *
     * Расположение планеты хранится в доске, не знаю,
     * есть ли смысл его хранить где-то кроме
     */
    public final int points;
    private Player owner;

    /**
     * Конструктор для генерации на поле
     * @param points
     */
    public Planet(final int points) {
        this.points = points;
        this.owner = null;
    }

    public int getPoints() {
        return points;
    }

    public Player getOwner() {
        return owner;
    }

    /**
     * Метод присвоения планеты игроку
     * @param player
     */
    public void setOwner(Player player){
        this.owner = player;
    }
}

