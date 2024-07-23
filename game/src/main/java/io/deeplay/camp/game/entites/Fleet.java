package io.deeplay.camp.game.entites;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public Fleet(final Cell fleetPosition, final Player player) {
        super();
        shipList = new ArrayList<>();
        this.fleetPosition = fleetPosition;
        owner = player;
        owner.addFleet(this);
    }

    //возможно вообще убрать этот конструктор? если мы всегда создаем корабль с флотом в конструкторе и там же его и добавляем во флот
    public Fleet(final List<Ship> shipList, final Cell fleetPosition, final Player player) {
        super();
        this.shipList = shipList;
        this.fleetPosition = fleetPosition;
        updateFleetPower();
        owner = player;
        owner.addFleet(this);
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


//    public void addShipsIntoFleet(List<Ship> ships) {
//        this.shipList.addAll(ships);
//        updateFleetPower();
//    }

    public void addShipIntoFleet(Ship ship) {
        this.shipList.add(ship);
        updateFleetPower();
    }

    public Map<Ship.ShipType, Integer> getShipsByShipType() {
        return null;
    }

    public void removeShipsFromFleet(Map<Ship.ShipType, Integer> shipsToRemove) {

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
