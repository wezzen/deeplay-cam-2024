package io.deeplay.camp.game.utils;

import io.deeplay.camp.game.entites.Cell;
import io.deeplay.camp.game.entites.Field;
import io.deeplay.camp.game.entites.Move;
import io.deeplay.camp.game.entites.Player;

public final class ValidationMove {
    public static boolean isValidOrdinaryMove(final Move move, final Field field, final Player currentPlayer, int cost) {
        return isPositionValid(move.endPosition(), field.getSize()) &&
                isOrdinaryMove(move) &&
                isPositionChanged(move) &&
                isFleetPresent(move) &&
                isOwnerFleet(move, currentPlayer) &&
                isEnoughPoints(currentPlayer, cost);
    }

    public static boolean isValidCaptureMove(final Move move, final Player currentPlayer, int cost){
        return isOwnerFleet(move, currentPlayer) &&
                isFleetPresent(move) &&
                isEnemyFleet(move, currentPlayer) &&
                isEnoughPoints(currentPlayer, cost) &&
                isCaptureMove(move);

    }
    // проверка - тип хода соответствует типу перемещения
    private static boolean isOrdinaryMove(final Move move) {
        return move.moveType() == Move.MoveType.ORDINARY;
    }
    private static boolean isCaptureMove(final Move move) {
        return move.moveType() == Move.MoveType.CAPTURE;
    }

    // проверка - флот не выйдет за границы карты
    private static boolean isPositionValid(final Cell newPosition, final int fieldSize) {
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

    // проверка - флот принадлежит игроку, который ходит
    private static boolean isOwnerFleet(final Move move, final Player currentPlayer) {
        return move.startPosition().getFleet().getOwner().equals(currentPlayer);
    }

    // проверка - игроку хватает очков, чтобы совершить ход
    private static boolean isEnoughPoints(Player player, int cost) {
        return player.getTotalGamePoints() - cost >= 0;
    }

    // проверка - на конечной точке есть флот оппонента
    private  static boolean isEnemyFleet(final Move move, final Player currentPlayer){
        return !move.endPosition().getFleet().getOwner().equals(currentPlayer) && move.endPosition().getFleet()!= null;
    }
}
