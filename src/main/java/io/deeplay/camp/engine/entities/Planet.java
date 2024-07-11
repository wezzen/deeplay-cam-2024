package io.deeplay.camp.engine.entities;

import java.util.Arrays;
import java.util.Objects;

/**
 * Класс – представление для Планет
 */
public class Planet {
    /**
     * Плнета имеет:
     * 1) Очки за захват пленеты
     * 2) Защищающую силу
     * 3) Владельца, если он есть
     * 4) Расположение
     */
    private final int points;
    protected int armor;
    private final Player owner;
    private final int[] coordinates;

    /**
     * Конструктор при наличии владельца
     * @param points
     * @param armor
     * @param owner
     * @param coordinates
     */
    public Planet(int points, int armor, Player owner, int[] coordinates) {
        this.points = points;
        this.armor = armor;
        this.owner = owner;
        this.coordinates = coordinates;
    }

    /**
     * Конструктор без владельца
     * @param points
     * @param armor
     * @param coordinates
     */
    public Planet(int points, int armor, int[] coordinates) {
        this.points = points;
        this.armor = armor;
        this.coordinates = coordinates;
        this.owner = null;
    }

    public int getArmor() {
        return armor;
    }

    public int getPoints() {
        return this.points;
    }


    public Player getOwner() {
        return owner;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    /**
     * Метод для контроля и обновления защитной силы планеты после захвата
     * @param fleet флот, захвативший планету
     */
    public void setControlledArmor(Fleet fleet) {
        this.armor += fleet.getFleetPower();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return points == planet.points && armor == planet.armor && Objects.equals(owner, planet.owner) && Arrays.equals(coordinates, planet.coordinates);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(points, armor, owner);
        result = 31 * result + Arrays.hashCode(coordinates);
        return result;
    }
}
