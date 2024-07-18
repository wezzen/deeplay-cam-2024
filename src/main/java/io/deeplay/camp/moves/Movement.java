package io.deeplay.camp.moves;

import io.deeplay.camp.engine.entities.Cell;

public final class Movement {

    // Проверка - новая позиция находится в пределах поля,
    public static boolean isValidBorder(int size, int newX, int newY) {

        return newX >= 0 && newX < size && newY >= 0 && newY < size;
    }

    // Проверка - новая позиция не занята другими объектами
    public static boolean isValidCell(Cell newPosition) {
        return newPosition.planet == null && newPosition.getFleet() == null;
    }

    // Проверка - новая позиция отличается от текущей позиции флота
    public static boolean isNewPosition(Cell fleetPosition, Cell newPosition) {
        return !newPosition.equals(fleetPosition);

    }
}