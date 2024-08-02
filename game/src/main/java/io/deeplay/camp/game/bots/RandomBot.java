package io.deeplay.camp.game.bots;

import io.deeplay.camp.game.domain.GameTypes;
import io.deeplay.camp.game.entites.*;
import io.deeplay.camp.game.interfaces.PlayerInterface;
import io.deeplay.camp.game.utils.PointsCalculator;
import io.deeplay.camp.game.utils.ValidationMove;

import java.util.*;

public class RandomBot extends Bot {
    private Random random;
    private List<Move> availableMoves;

    protected RandomBot(final String name, final Field field) {
        super(name, field);  // Передаем копию поля
        this.random = new Random();
    }

    /**
     * Выполняет ход для бота. Метод определяет, какой ход бот должен сделать на основе текущего состояния игры,
     * выбирает случайный доступный ход и исполняет его.
     *
     * @return объект {@link Move}, представляющий совершенный ход. Если доступных ходов нет, возвращается ход типа SKIP.
     *
     * @throws RuntimeException если нет клеток с флотом, принадлежащим игроку.
     * @throws IllegalArgumentException если тип хода не поддерживается.
     *
     * Метод сначала проверяет наличие флота у игрока на игровом поле. Если флот отсутствует, возвращается ход типа SKIP.
     * Далее выбирается клетка с флотом игрока и добавляются возможные ходы флота. Если доступных ходов нет, возвращается ход типа SKIP.
     * Иначе выбирается случайный ход из доступных и проверяется его валидность. В зависимости от типа хода, он либо выполняется,
     * либо бросается исключение, если тип хода не поддерживается.
     */
    @Override
    public Move getMove() {
        final Field field = game.getField();  // Получаем копию поля
        final Cell[][] board = field.getBoard();
        final Player player = game.getPlayerByName(name);

        long fleetCount = Arrays.stream(board)
                .flatMap(Arrays::stream)
                .filter(cell -> cell.getFleet() != null && Objects.equals(cell.getFleet().getOwner().getName(), player.getName())).count();
        if (fleetCount == 0) {
            return new Move(null, null, Move.MoveType.SKIP, 0);
        }

        Cell startCell = Arrays.stream(board)
                .flatMap(Arrays::stream)
                .filter(cell -> cell.getFleet() != null && Objects.equals(cell.getFleet().getOwner().getName(), player.getName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Нет клеток с флотом"));
        startCell.getFleet().addFleetMoves(field);

        availableMoves = startCell.getFleet().getFleetMoves();
        availableMoves.removeIf(move -> PointsCalculator.costMovement(move) > player.getTotalGamePoints());

        if (availableMoves.isEmpty()) {
            return new Move(null, null, Move.MoveType.SKIP, 0);
        }

        return availableMoves.get(random.nextInt(availableMoves.size()));
    }

    public static class Factory extends BotFactory {

        public Factory() {
        }

        @Override
        public RandomBot createBot(final String name, final Field field) {
            return new RandomBot(name, field);  // Передаем копию поля
        }
    }
}
