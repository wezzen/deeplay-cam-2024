package io.deeplay.camp.game.bots;

import io.deeplay.camp.game.entites.*;
import io.deeplay.camp.game.utils.PointsCalculator;
import io.deeplay.camp.game.utils.ValidationMove;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomBot extends Bot {
    public final Player player;
    private final Field field;
    private Random random;
    private List<Move> availableMoves;

    protected RandomBot(Field field, Player player) {
        super(field);
        this.player = player;
        this.field = field;
        this.random = new Random();
    }

    @Override
    public Move getMove() {
        Cell[][] board = field.getBoard();


        Cell startCell = Arrays.stream(field.getBoard())
                .flatMap(Arrays::stream)
                .filter(cell -> cell.getFleet() != null && cell.getFleet().getOwner() == this.player)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Нет клеток с флотом"));
        startCell.getFleet().addFleetMoves(field);

        availableMoves = startCell.getFleet().getFleetMoves();
        Move move = availableMoves.get(random.nextInt(availableMoves.size()));

        if (availableMoves.isEmpty()) {
            throw new RuntimeException("Нет клеток совершения для хода");
        }

        if (move.moveType() == Move.MoveType.ORDINARY) {
            move.makeMove(player);
        } else if (move.moveType() == Move.MoveType.CAPTURE) {
            move.makeAttack(player);
        } else {
            throw new IllegalArgumentException("Нет такого типа хода!");
        }
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
