package io.deeplay.camp.game.entites;

import java.util.List;
import java.util.Objects;

/**
 * Класс-представление сущности Флот
 */
public class Fleet extends GalaxyEntity {
    /**
     * Флот хранит в себе переменные:
     * 1) shipList - для хранения актуального набора кораблей
     * 2) fleetPower - для хранения суммы сил всех кораблей флота
     * 3) fleetPosition - для клетки - расположения
     */
    private List<Ship> shipList;
    private Cell fleetPosition;
    private int fleetPower;
    private final Player owner;

    public Fleet(final List<Ship> shipList, final Cell fleetPosition, final Player player) {
        super();
        this.shipList = shipList;
        this.fleetPosition = fleetPosition;
        updateFleetPower();
        owner = player;
    }

    /**
     * Метод для актуализации значения силы Флота для случая,
     * когда у нас пополнение листа или перераспределение кораблей
     */
    private void updateFleetPower() {
        this.fleetPower = shipList.stream().mapToInt(ship -> ship.getShipType().getShipPower()).sum();
    }

    public int getFleetPower() {
        return fleetPower;
    }

    /**
     * Метод для обновления силы флота, если добавим корабль во флот
     *
     * @param ship добавляемый корабль
     */
    public void actualFleetPower(final Ship ship) {
        shipList.add(ship);
        updateFleetPower();
    }

    /**
     * Изменение флота. Объединение флотов
     *
     * @param fleet         флот
     * @param increasePower флаг на то, добавляем или удаляем
     */
    public void updateShipList(final Fleet fleet, final boolean increasePower) {
        if (increasePower) {
            this.shipList.addAll(fleet.getShipList());
        } else {
            this.shipList.removeAll(fleet.getShipList());
        }
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
        } else {
            this.shipList.removeAll(newShipList);
        }
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
        } else {
            this.shipList.remove(ship);
        }
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

    /**
     * Метод, который обыгрывает столкновение флотов
     *
     * @param enemyFleet флот соперника
     * @param us         игрок
     * @param enemy      игрок
     */
    public void fleetsClash(final Fleet enemyFleet, final Player us, final Player enemy) {
        if (this.fleetPower > enemyFleet.fleetPower) {
            enemy.removeFleet(enemyFleet);
        } else {
            us.removeFleet(this);
        }
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
