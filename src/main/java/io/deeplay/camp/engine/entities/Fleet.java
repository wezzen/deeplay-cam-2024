package io.deeplay.camp.engine.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Класс-представление сущности Флот
 */
public class Fleet {
    /**
     * Флот хранит в себе переменные:
     * 1) shipList - для хранения актуального набора кораблей
     * 2) fleetPower - для хранения суммы сил всех кораблей флота
     * 3) fleetPosition - координату x и y расположения корабля на поле
     * 4) isFirstMove - флажок
     */
    private final ArrayList<Ship> shipList;
    private int fleetPower;
    private final int[] fleetPosition;

    /**
     * Конструктор Флота по значениям
     * @param shipList
     * @param fleetPosition
     */
    public Fleet(ArrayList<Ship> shipList, int[] fleetPosition) {
        this.shipList = shipList;
        this.fleetPosition = fleetPosition;
        this.updateFleetPower();
    }

    public int[] getFleetPosition() {
        return fleetPosition;
    }
    public int getFleetPower() {
        return fleetPower;
    }

    /**
     * Метод для актуализации значения силы Флота для случая,
     * когда у нас пополнение листа или перераспределение кораблей
     */
    private void updateFleetPower() {
        int totalPower = 0;
        for (Ship ship : shipList) {
            totalPower += ship.getAttackPoints();
        }
        this.fleetPower = totalPower;
    }
    public void actualFleetPower(ArrayList<Ship> shipList) {
        int totalPower = 0;
        for (Ship ship : shipList) {
            totalPower += ship.getAttackPoints();
        }
        this.fleetPower = totalPower;
    }
    public void actualFleetPower(Ship ship) {
        int totalPower = fleetPower;
        totalPower += ship.getAttackPoints();
        this.fleetPower = totalPower;
    }

    public void updateShipList(ArrayList<Ship> newShipList) {
        this.shipList.addAll(newShipList);
        this.updateFleetPower();
    }public void updateShipList(Ship newShipList) {
        this.shipList.add(newShipList);
        this.updateFleetPower();
    }

    public ArrayList<Ship> getShipList() {
        return shipList;
    }
}
