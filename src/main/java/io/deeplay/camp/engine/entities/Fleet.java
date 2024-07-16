package io.deeplay.camp.engine.entities;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Класс-представление сущности Флот
 */
public class Fleet {
    /**
     * Флот хранит в себе переменные:
     * 1) shipList - для хранения актуального набора кораблей
     * 2) fleetPower - для хранения суммы сил всех кораблей флота
     * 3) fleetPosition - для клетки - расположения
     */
    private ArrayList<Ship> shipList;
    private Cell fleetPosition;
    private int fleetPower;

    public Fleet(ArrayList<Ship> shipList, Cell fleetPosition) {
        this.shipList = shipList;
        this.fleetPosition = fleetPosition;
        updateFleetPower();
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
    public int getFleetPower() {
        return fleetPower;
    }

    /**
     * Метод для обновления силы флота, если организуем новый флот
     * @param shipList
     */
    public void actualFleetPower(ArrayList<Ship> shipList) {//Написать в геттер, чтобы мы не тянули пустую, убрать фложенное поле
        int totalPower = 0;
        for (Ship ship : shipList) {
            totalPower += ship.getAttackPoints();
        }
        this.fleetPower = totalPower;
    }

    /**
     * Метод для обновления силы флота, если добавим корабль во флот
     * @param ship
     */
    public void actualFleetPower(Ship ship) {
        int totalPower = fleetPower;
        totalPower += ship.getAttackPoints();
        this.fleetPower = totalPower;
    }

    /**
     * Изменение флота. Обьединение флотов
     * @param fleet
     * @param increasePower
     */
    public void updateShipList(Fleet fleet, boolean increasePower) {
        if (increasePower) {
            this.shipList.addAll(fleet.getShipList());
        }
        else this.shipList.removeAll(fleet.getShipList());
        this.updateFleetPower();
    }

    /**
     * Изменение флота. Добавление набора кораблей
     * @param newShipList
     * @param increasePower
     */
    public void updateShipList(ArrayList<Ship> newShipList, boolean increasePower) {
        if (increasePower) {
            this.shipList.addAll(newShipList);
        }
        else this.shipList.removeAll(newShipList);
        this.updateFleetPower();
    }

    /**
     * Изменение флота. Добавление корабля
     * @param ship
     * @param increasePower
     */
    public void updateShipList(Ship ship, boolean increasePower) {
        if (increasePower) {
            this.shipList.add(ship);
        }
        else this.shipList.remove(ship);
        this.updateFleetPower();
    }

    public ArrayList<Ship> getShipList() {
        return shipList;
    }

    public Cell getFleetPosition() {
        return fleetPosition;
    }
    public void setFleetPosition(Cell position) {
        this.fleetPosition = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fleet fleet = (Fleet) o;
        return fleetPower == fleet.fleetPower && Objects.equals(shipList, fleet.shipList) && Objects.equals(fleetPosition, fleet.fleetPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shipList, fleetPosition, fleetPower);
    }
}
