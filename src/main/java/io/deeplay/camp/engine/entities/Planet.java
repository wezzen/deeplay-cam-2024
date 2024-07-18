package io.deeplay.camp.engine.entities;

import java.util.Objects;

/**
 * Класс планеты
 */
public class Planet {
    /**
     * Плнета имеет:
     * 1) Очки за захват пленеты
     * 2) Владельца, если он есть
     * Расположение планеты хранится в доске, не знаю,
     * есть ли смысл его хранить где-то кроме
     */
    public final int points;
    private Player owner;

    /**
     * Конструктор для генерации на поле
     *
     * @param points очки базовой защиты
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
     * @return если захвачена true, если свободна false
     */
    public boolean isCaptured() {
        return owner != null;
    }

    /**
     * Метод присвоения планеты игроку
     *
     * @param player игрок
     */
    public void setOwner(final Player player) {
        this.owner = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return points == planet.points && Objects.equals(owner, planet.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(points, owner);
    }
}

