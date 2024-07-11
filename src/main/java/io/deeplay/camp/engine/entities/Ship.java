package io.deeplay.camp.engine.entities;

import java.util.Collection;
import java.util.Objects;

public abstract class Ship {
    int attackPoints;
    final ShipType shipType;
    private final boolean isFirstIncome; //Пока не могу про принадлежность этого поля что-то сказать
    private final int cachedHashCode;

    protected Ship(ShipType shipType, boolean isFirstMove) {
        this.shipType = shipType;
        this.isFirstIncome = isFirstMove;
        this.cachedHashCode = hashCode();
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public boolean isFirstIncome() {
        return isFirstIncome;
    }

    public abstract int locationBonus();

    public abstract Ship replenishFleet(Fleet fleet);

    public abstract Collection<Move> calculateLegalReplenish(final Field field);


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return attackPoints == ship.attackPoints && isFirstIncome == ship.isFirstIncome && cachedHashCode == ship.cachedHashCode && shipType == ship.shipType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(attackPoints, shipType, isFirstIncome, cachedHashCode);
    }

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
