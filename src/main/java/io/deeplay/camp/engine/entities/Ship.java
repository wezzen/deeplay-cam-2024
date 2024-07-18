package io.deeplay.camp.engine.entities;

import java.util.Objects;

/**
 * Класс – представлние для Корабля
 */
public class Ship {
    /**
     * Корабль имеет:
     * 1) атакующие очки
     * 2) Тип
     * 3) флот, для контроля принадлежности к нему
     */
    int attackPoints;
    final ShipType shipType;
    private Fleet fleetAffiliation;

    public void setFleetAffiliation(final Fleet fleetAffiliation) {
        this.fleetAffiliation = fleetAffiliation;
        this.fleetAffiliation.updateShipList(this, true);
    }

    public Ship(ShipType shipType, final Fleet fleetAffiliation) {
        this.shipType = shipType;
        this.fleetAffiliation = fleetAffiliation;
        this.attackPoints = shipType.shipPower;
    }

    /**
     * Конструткор для корабля, которому еще не присвоили флот
     *
     * @param shipType тип корабля
     */
    public Ship(final ShipType shipType) {
        this(shipType, null);
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public Fleet fleetAffiliation() {
        return this.fleetAffiliation;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Ship ship)) return false;
        return attackPoints == ship.attackPoints && shipType == ship.shipType && Objects.equals(fleetAffiliation, ship.fleetAffiliation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attackPoints, shipType, fleetAffiliation);
    }

    /**
     * Набор всевозможных кораблей в игре
     * <p>
     * Все корабли имеют:
     * 1) Очки атаки
     * 2) Имя стрингой
     */
    public enum ShipType {
        BASIC(100, "Basic");
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

