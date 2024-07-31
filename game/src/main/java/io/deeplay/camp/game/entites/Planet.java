package io.deeplay.camp.game.entites;

import java.util.Objects;

/**
 * Класс планеты
 */
public class Planet extends GalaxyEntity {
    /**
     * Планета имеет:
     * 1) Очки за захват планеты
     * 2) Владельца, если он есть
     * Расположение планеты хранится в доске, не знаю,
     * есть ли смысл его хранить где-то кроме
     */
    public final int points; // пока пусть эти очки будут очками защиты планеты
    private Player owner;
    private Cell cell = null;

    /**
     * Конструктор для генерации на поле
     *
     * @param points очки базовой защиты
     */
    public Planet(final int points) {
        super();
        this.points = points;
        this.owner = null;
    }

    public void setCell(final Cell cell) throws RuntimeException {
        if (this.cell != null) {
            throw
                    new IllegalArgumentException("Поле уже присвоено");
        }
        this.cell = cell;
    }

    public int getPoints() {
        return points;
    }

    public Player getOwner() {
        return owner;
    }

    /**
     * *return если захвачена true, если свободна false
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
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Planet planet)) return false;
        return points == planet.points && Objects.equals(owner, planet.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(points, owner);
    }
}

