package io.deeplay.camp.engine.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Fleet {//Не понимаю как будет работать флот. Это инструмент для хода? Опирался на это
    //final HashMap<> fleetPosition; Все зависит от устройства поля
    private final ArrayList<Ship> shipList; //Набор кораблей во флоте
    private final int fleetPower; //Сумма сил всех кораблей флота
    private final int[] fleetPosition; //Пока что везде фоткнул массив

    public Fleet(ArrayList<Ship> shipList, int fleetPower, int[] fleetPosition) {
        this.shipList = shipList;
        this.fleetPower = fleetPower;
        this.fleetPosition = fleetPosition;
    }

    public boolean isFirstMove() { //Еще не обработан метод
        return true;
    }

    public int[] getFleetPosition() { //Позиция флота всегда нам известна
        return fleetPosition;
    }

    public boolean inAttack() { //Воткнул как заглушку
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fleet fleet = (Fleet) o;
        return fleetPower == fleet.fleetPower && Objects.equals(shipList, fleet.shipList) && Arrays.equals(fleetPosition, fleet.fleetPosition);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(shipList, fleetPower);
        result = 31 * result + Arrays.hashCode(fleetPosition);
        return result;
    }

    public int getFleetPower() {
        int totalPower = 0;
        for (Ship ship : shipList) {
            totalPower += ship.getAttackPoints();
        }
        return totalPower;
    }
}
