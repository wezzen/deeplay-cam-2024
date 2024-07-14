package io.deeplay.camp.engine.entities;

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
    private final Fleet fleetAffiliation;

    protected Ship(ShipType shipType, Fleet fleetAffiliation) {
        this.shipType = shipType;
        this.fleetAffiliation = fleetAffiliation;
        attackPoints = shipType.shipPower;
    }

    /**
     * Конструткор для корабля, которому еще не присвоили флот
     * @param shipType
     */
    public Ship(ShipType shipType) {
        this.shipType = shipType;
        this.fleetAffiliation = null;
        attackPoints = shipType.shipPower;
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

    /**
     * Набор всевозможных кораблей в игре
     *
     * Все корабли имеют:
     * 1) Очки атаки
     * 2) Имя стрингой
     */
    public enum ShipType {
        BASIC(100, "Basic");
        private final int shipPower;
        private final String shipName;

        ShipType (int shipPower, String shipName) {
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

