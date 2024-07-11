package io.deeplay.camp.engine.entities;

import java.util.Collection;
import java.util.Objects;

/**
 * Класс – представлние для Корабля
 */
public abstract class Ship {
    /**
     * Корабль имеет:
     * 1) атакующие очки
     * 2) Тип
     * 3) флот, для контроля принадлежности к нему
     * 4) хэшкод для распознования корабля
     */
    int attackPoints;
    final ShipType shipType;
    private final Fleet fleetIncome;

    protected Ship(ShipType shipType, Fleet fleetIncome) {
        this.shipType = shipType;
        this.fleetIncome = fleetIncome;
        attackPoints = shipType.value;
    }
    public Ship(ShipType shipType) {
        this.shipType = shipType;
        this.fleetIncome = null;
        attackPoints = shipType.value;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public Fleet fleetIncome() {
        return this.fleetIncome;
    }

    public abstract Ship replenishFleet(Fleet fleet);
    public abstract Collection<Move> calculateLegalReplenish();

    public enum ShipType {
        BASIC(100, "Basic");
        private final int value;
        private final String shipName;

        public int getPieceValue() {
            return this.value;
        }

        @Override
        public String toString() {
            return this.shipName;
        }
        ShipType (int value, String shipName) {
            this.value = value;
            this.shipName = shipName;
        }

    }
}
