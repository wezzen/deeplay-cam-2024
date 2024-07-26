package io.deeplay.camp.game.bots;

import io.deeplay.camp.game.entites.Cell;
import io.deeplay.camp.game.entites.Field;
import io.deeplay.camp.game.entites.Move;
import io.deeplay.camp.game.entites.Player;

import java.util.List;
import java.util.Random;

public class RandomBot extends Bot {
    private final Player player;
    private final Field field;
    protected RandomBot(Field field, Player player) {
        super(field);
        this.player = player;
        this.field = field;
    }
    @Override
    protected Move getMove() {
        Cell[][] board = field.getBoard();
        Cell startCell = getRandomCell(board);
        Cell endCell = getRandomCell(board);
        Move.MoveType moveType = Move.MoveType.ORDINARY; // или другая логика определения типа хода

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
        Random random = new Random();
        int row = random.nextInt(board.length);
        int col = random.nextInt(board[row].length);
        return board[row][col];
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
