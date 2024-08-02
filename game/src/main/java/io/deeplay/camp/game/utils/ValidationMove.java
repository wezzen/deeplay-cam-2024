package io.deeplay.camp.game.utils;

import io.deeplay.camp.game.entites.Cell;
import io.deeplay.camp.game.entites.Field;
import io.deeplay.camp.game.entites.Move;
import io.deeplay.camp.game.entites.Player;

public final class ValidationMove {
    public static boolean isValidOrdinaryMove(final Move move, final Field field, final Player currentPlayer) {
        return isPositionValid(move.endPosition(), field.getSize()) &&
                isOrdinaryMove(move) &&
                isPositionChanged(move) &&
                isFleetPresent(move) &&
                isOwnerFleet(move, currentPlayer) &&
                isEnoughPoints(currentPlayer, move);
    }

    public static boolean isValidCaptureMove(final Move move, final Player currentPlayer) {
        return isOwnerFleet(move, currentPlayer) &&
                isFleetPresent(move) &&
                isEnoughPoints(currentPlayer, move) &&
                isPositionChanged(move) &&
                isCaptureMove(move) &&
                (isEnemyFleet(move, currentPlayer) ||
                isPlanet(move.endPosition()));

    }

    // проверка - тип хода соответствует типу перемещения
    private static boolean isOrdinaryMove(final Move move) {
        return move.moveType() == Move.MoveType.ORDINARY;
    }

    private static boolean isCaptureMove(final Move move) {
        return move.moveType() == Move.MoveType.CAPTURE;
    }

    private static boolean isSkipMove(final Move move) {
        return move.moveType() == Move.MoveType.SKIP;
    }

    // проверка - флот не выйдет за границы карты
    public static boolean isPositionValid(final Cell newPosition, final int fieldSize) {
        return newPosition.x >= 0 && newPosition.x < fieldSize && newPosition.y >= 0 && newPosition.y < fieldSize;
    }

    // проверка - начальная и конечная позиция отличается
    private static boolean isPositionChanged(final Move move) {
        return !move.startPosition().equals(move.endPosition()) || move.startPosition().planet != null;
    }

    // проверка - на начальной позиции стоит флот
    public static boolean isFleetPresent(final Move move) {
        return move.startPosition().getFleet() != null;
    }

    // проверка - флот принадлежит игроку, который ходит
    private static boolean isOwnerFleet(final Move move, final Player currentPlayer) {
        return move.startPosition().getFleet().getOwner().equals(currentPlayer);
    }

    // проверка - игроку хватает очков, чтобы совершить ход
    public static boolean isEnoughPoints(Player player, Move move) {
        return player.getTotalGamePoints() - move.cost() >= 0;
    }

    // проверка - на конечной точке есть флот оппонента
    private static boolean isEnemyFleet(final Move move, final Player currentPlayer) {
        return !move.endPosition().getFleet().getOwner().equals(currentPlayer) && move.endPosition().getFleet() != null;
    }

    private static boolean isPlanet(Cell planetCell) {
        return planetCell.planet != null;
    }
    // проверка - флоту хватает очков для захвата планеты
    public static boolean isCapturePlanet(final int powerFleet, final int pointPlanet) {
        return powerFleet >= pointPlanet;
    }
}
