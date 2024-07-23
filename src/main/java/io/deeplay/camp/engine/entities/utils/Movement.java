package io.deeplay.camp.engine.entities.utils;

import io.deeplay.camp.engine.entities.Cell;
import io.deeplay.camp.engine.entities.Field;
import io.deeplay.camp.engine.entities.move.Move;


public final class Movement {

    public static boolean isValidMove(final Move move, final Field field) {
        return isPositionValid(move.endPosition(), field.getSize()) &&
                isOrdinaryMove(move) &&
                isPositionChanged(move) &&
                isFleetPresent(move);
    }
    // проверка - тип хода соответствует типу перемещение
    private static boolean isOrdinaryMove(final Move move){
        return move.moveType() == Move.MoveType.ORDINARY;
    }
    // проверка - флот не выйдет за границы карты
    private static boolean isPositionValid(final Cell newPosition,final int fieldSize) {
        return newPosition.x >= 0 && newPosition.x < fieldSize && newPosition.y >= 0 && newPosition.y < fieldSize;
    }
    // проверка - начальная и конечная позиция отличается
    private static boolean isPositionChanged(final Move move) {
        return !move.startPosition().equals(move.endPosition());
    }
    // проверка - на начальной позиции стоит флот
    private static boolean isFleetPresent(final Move move) {
        return move.startPosition().getFleet() != null;
    }
}
