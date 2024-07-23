package io.deeplay.camp.game.entites;

import java.util.Objects;

/**
 * Класс – представление для Корабля
 */
public class Ship {
    /**
     * Корабль имеет:
     * 1) Тип
     * 2) флот, для контроля принадлежности к нему
     */
    final ShipType shipType;
    private Fleet fleetAffiliation;

    public void setFleetAffiliation(final Fleet fleetAffiliation) {
        this.fleetAffiliation = fleetAffiliation;
        this.fleetAffiliation.addShipIntoFleet(this);
    }

    /**
     * Конструктор для корабля
     *
     * @param shipType         тип инициализируемого корабля
     * @param fleetAffiliation флот, к которому относится корабль
     */
    public Ship(final ShipType shipType, final Fleet fleetAffiliation) {
        this.shipType = shipType;
        setFleetAffiliation(fleetAffiliation);
    }

    public ShipType getShipType() {
        return shipType;
    }

    public Fleet fleetAffiliation() {
        return this.fleetAffiliation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return shipType == ship.shipType && Objects.equals(fleetAffiliation, ship.fleetAffiliation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shipType, fleetAffiliation);
    }


    /**
     * Набор всевозможных кораблей в игре
     * <p>
     * Все корабли имеют:
     * 1) Очки атаки
     * 2) Имя корабля
     */
    public enum ShipType {
        BASIC(100, "Basic"),
        MEDIUM(150, "Medium"),
        POWERFUL(200, "Powerful");

        private final int shipPower;
        private final String shipName;

        ShipType(int shipPower, String shipName) {
            this.shipPower = shipPower;
            this.shipName = shipName;
        }

        public int getShipPower() {
            return this.shipPower;
        }

        @Override
        public String toString() {
            return this.shipName;
        }
    }
}
