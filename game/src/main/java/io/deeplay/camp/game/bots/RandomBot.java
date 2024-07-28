package io.deeplay.camp.game.bots;

import io.deeplay.camp.game.entites.Cell;
import io.deeplay.camp.game.entites.Field;
import io.deeplay.camp.game.entites.Move;
import io.deeplay.camp.game.entites.Player;
import io.deeplay.camp.game.utils.PointsCalculator;
import io.deeplay.camp.game.utils.ValidationMove;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomBot extends Bot {
    private final Player player;
    private final Field field;
    private Random random;
    private List<Cell> availableCells;

    protected RandomBot(Field field, Player player) {
        super(field);
        this.player = player;
        this.field = field;
        this.random = new Random();
    }

    @Override
    protected Move getMove() {
        Cell[][] board = field.getBoard();
        Cell startCell = Arrays.stream(field.getBoard())
                .flatMap(Arrays::stream)
                .filter(cell -> cell.getFleet() != null)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No cell with a fleet found"));
        availableCells = getAvailableCells(startCell, board);
        if (availableCells.isEmpty()) {
            throw new RuntimeException("No available cells for a move");
        }
        Cell endCell = availableCells.get(random.nextInt(availableCells.size()));
        Move.MoveType moveType = Move.MoveType.ORDINARY;

        Move move = new Move(startCell, endCell, moveType);
        move.makeMove(player);
        return move;
    }

    /**
     * Метод для получения случайной клетки из игрового поля.
     *
     * @param board двумерный массив клеток игрового поля.
     * @return случайная клетка.
     */
    protected Cell getRandomCell(Cell[][] board) {
        int row = random.nextInt(board.length);
        int col = random.nextInt(board[row].length);
        return board[row][col];
    }

    /**
     * Метод для получения всех доступных клеток для хода.
     *
     * @param startCell начальная клетка.
     * @param board     игровое поле.
     * @return список доступных клеток.
     */
    private List<Cell> getAvailableCells(Cell startCell, Cell[][] board) {
        List<Cell> availableCells = new ArrayList<>();
        for (Cell[] row : board) {
            for (Cell cell : row) {
                Move move = new Move(startCell, cell, Move.MoveType.ORDINARY);
                int cost = PointsCalculator.costMovement(move);
                if (ValidationMove.isValidOrdinaryMove(move, field, player, cost)) {
                    availableCells.add(cell);
                }
            }
        }
        return availableCells;
    }

    public static class Factory extends BotFactory {
        private final Player player;

        public Factory(Player player) {
            this.player = player;
        }

        @Override
        public RandomBot createBot(final Field field) {
            return new RandomBot(field, player);
        }
    }
}
