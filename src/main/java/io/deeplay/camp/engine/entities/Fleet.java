package io.deeplay.camp.engine.entities;

import java.util.ArrayList;
import java.util.List;
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
    private List<Ship> shipList;
    private Cell fleetPosition;
    private int fleetPower;

    public Fleet(final List<Ship> shipList, final Cell fleetPosition) {
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
     *
     * @param addedShipList корабли которые мы добавляем во флот
     */
    public void actualFleetPower(final List<Ship> addedShipList) {
        int totalPower = 0;
        for (Ship ship : addedShipList) {
            totalPower += ship.getAttackPoints();
        }
        updateShipList(addedShipList, true);
        this.fleetPower = totalPower;
    }

    /**
     * Метод для обновления силы флота, если добавим корабль во флот
     *
     * @param ship добавляемый корабль
     */
    public void actualFleetPower(final Ship ship) {
        int totalPower = fleetPower;
        totalPower += ship.getAttackPoints();
        this.fleetPower = totalPower;
        shipList.add(ship);
    }

    /**
     * Изменение флота. Обьединение флотов
     *
     * @param fleet         флот
     * @param increasePower флаг на то, добавляем или удаляем
     */
    public void updateShipList(final Fleet fleet, final boolean increasePower) {
        if (increasePower) {
            this.shipList.addAll(fleet.getShipList());
        } else this.shipList.removeAll(fleet.getShipList());
        this.updateFleetPower();
    }

    /**
     * Изменение флота. Добавление набора кораблей
     *
     * @param newShipList   флот
     * @param increasePower флаг на то, добавляем или удаляем
     */
    public void updateShipList(final List<Ship> newShipList, final boolean increasePower) {
        if (increasePower) {
            this.shipList.addAll(newShipList);
        } else this.shipList.removeAll(newShipList);
        this.updateFleetPower();
    }

    /**
     * Изменение флота. Добавление корабля
     *
     * @param ship          корабль
     * @param increasePower флаг на то, добавляем или удаляем
     */
    public void updateShipList(final Ship ship, final boolean increasePower) {
        if (increasePower) {
            this.shipList.add(ship);
        } else this.shipList.remove(ship);
        this.updateFleetPower();
    }

    public List<Ship> getShipList() {
        return shipList;
    }

    public Cell getFleetPosition() {
        return fleetPosition;
    }

    //не делаю final из-за взаимной зависимости, возможно позже надо будет сетить в клетку
    public void setFleetPosition(Cell position) {
        this.fleetPosition = position;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Fleet fleet)) return false;
        return Objects.equals(shipList, fleet.shipList) &&
                Objects.equals(fleetPosition, fleet.fleetPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shipList, fleetPosition);
    }
}

