package io.deeplay.camp.game.entites;

import java.util.*;

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

    public Fleet(final List<Ship> AnotherShipList, final Cell fleetPosition, final Player player) {
        super();
        this.shipList = new ArrayList<>();
        for (Ship ship : AnotherShipList) {
            ship.setFleetAffiliation(this);
        }
        this.fleetPosition = fleetPosition;
        updateFleetPower();
        owner = player;
        owner.addFleet(this);
    }


    public boolean checkShipExist(final Ship checkedShip) {
        for (Ship ship : shipList) {
            if (checkedShip.getId() == ship.getId()) {
                return true;
            }
        }
        return false;
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


    public void addShipsIntoFleet(List<Ship> ships) {

        for (Ship ship : ships) {
            if (checkShipExist(ship)) {
                throw new IllegalArgumentException("корабль уже существует во флоте");
            }
            ship.setFleetAffiliation(this);
        }
        updateFleetPower();
    }

    public void addShipIntoFleet(Ship ship) {
        if (checkShipExist(ship)) {
            throw new IllegalArgumentException("корабль уже существует во флоте");
        }
        this.shipList.add(ship);
        ship.fleetAffiliation();
        updateFleetPower();
    }

    public Map<Ship.ShipType, Integer> getShipsByShipType() {
        Map<Ship.ShipType, Integer> result = new HashMap<>();

        for (Ship ship : shipList) {
            Ship.ShipType shipType = ship.getShipType();
            result.put(shipType, result.getOrDefault(shipType, 0) + 1);
        }

        return result;
    }

    public List<Ship> removeShipsFromFleet(Map<Ship.ShipType, Integer> shipsToRemove) {
        List<Ship> result = new ArrayList<>();
        for (Map.Entry<Ship.ShipType, Integer> entry : shipsToRemove.entrySet()) {
            Ship.ShipType shipType = entry.getKey();
            int countToRemove = entry.getValue();

            Iterator<Ship> iterator = shipList.iterator();
            while (iterator.hasNext() && countToRemove > 0) {
                Ship ship = iterator.next();
                if (ship.getShipType() == shipType) {
                    result.add(ship);
                    iterator.remove();
                    countToRemove--;
                }
            }

            if (countToRemove > 0) {
                throw new IllegalArgumentException("Неверное количество кораблей для удаления");
            }
        }
        updateFleetPower();
        return result;
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

    public Player getOwner() {
        return owner;
    }
}
