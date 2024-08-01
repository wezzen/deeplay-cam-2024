package io.deeplay.camp.game.utils;

import io.deeplay.camp.game.entites.Cell;
import io.deeplay.camp.game.entites.Move;

public final class PointsCalculator {
    public static final int DIRECT_COST = 5;
    public static final int DIAGONAL_COST = 7;

    public static int costMovement(Move move) {
        if (move.startPosition() == null && move.endPosition() == null) {
            return 0;
        }
        Cell start = move.startPosition();
        Cell end = move.endPosition();
        // Здесь можно добавить логику для использования totalCost
        return calculateCostMovement(start, end);
    }

    private static int calculateCostMovement(Cell start, Cell end) {
        int deltaX = Math.abs(end.x - start.x);
        int deltaY = Math.abs(end.y - start.y);
        int directMoves = Math.abs(deltaX - deltaY);
        int diagonalMoves = Math.max(deltaX, deltaY) - directMoves;
        return calculateTotalCost(directMoves, diagonalMoves);
    }

    private static int calculateTotalCost(int direct, int diagonal) {
        return direct * DIRECT_COST + diagonal * DIAGONAL_COST;
    }

    public static int costForList(Cell currentCell, int newX, int newY) {
        return currentCell.y != newY && currentCell.x != newX ? DIAGONAL_COST : DIRECT_COST;
    }

}
