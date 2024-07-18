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
    private final List<Ship> shipList;
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
     * Класс для обновления силы флота, если организуем новый флот
     *
     * @param shipList корабли которые мы добавляем во флот
     */
    public void actualFleetPower(List<Ship> shipList) {
        int totalPower = 0;
        for (Ship ship : shipList) {
            totalPower += ship.getAttackPoints();
            shipList.add(ship);
        }
        this.fleetPower = totalPower;
    }

    /**
     * метод для обновления силы флота, если добавим корабль во флот
     *
     * @param ship добавляемый корабль
     */
    public void actualFleetPower(Ship ship) {
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
    public void updateShipList(Fleet fleet, boolean increasePower) {
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
    public void updateShipList(List<Ship> newShipList, boolean increasePower) {
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
    public void updateShipList(Ship ship, boolean increasePower) {
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

    public void setFleetPosition(Cell position) {
        this.fleetPosition = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fleet fleet = (Fleet) o;
        return Objects.equals(shipList, fleet.shipList) &&
                Objects.equals(fleetPosition, fleet.fleetPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shipList, fleetPosition);
    }
}

