package io.deeplay.camp.game.bots;

import io.deeplay.camp.game.domain.GameTypes;
import io.deeplay.camp.game.entites.*;
import io.deeplay.camp.game.interfaces.PlayerInterface;
import io.deeplay.camp.game.utils.PointsCalculator;
import io.deeplay.camp.game.utils.ValidationMove;

/**
 * Абстрактный класс Бот. В дальнейшем будет родителем
 * для разных ботов в игре.
 * <p>
 * Реализует интерфейс PlayerInterface, чтобы дать возможность
 * совершать боту ходы.
 */
public abstract class Bot implements PlayerInterface {
    private static final int NUM_PLAYERS = 2;
    /**
     * В классе есть только экземпляр класса Game
     * aka контроллер
     */
    private final Game game;

    protected Bot(final Field field) {
        this.game = new Game(field);
    }

    @Override
    public Answer getAnswer(final Field field) {
        return new Answer(getMove());
    }

    protected abstract Move getMove();

    @Override
    public void startGameSession(final String gameId, final GameTypes gameType) {
        game.startGameSession(gameId, gameType);
    }

    @Override
    public void connectingPlayer(final String waitingPlayerName) {
        //game.connectingPlayer(waitingPlayerName);
        if (game.players[0] == null) {
            game.players[0] = new Player(0, waitingPlayerName);
            game.getPlayerNames().put(waitingPlayerName, game.players[0]);
        } else if (game.players[1] == null) {
            game.players[1] = new Player(1, waitingPlayerName);
            game.getPlayerNames().put(waitingPlayerName, game.players[1]);
        } else {
            throw new IllegalArgumentException("Игроки уже существуют");
        }
    }

    @Override
    public void gameStarted(final Field field) {
        game.gameStarted(field);
    }

    @Override
    public void getPlayerAction(final Move move, final String playerName) {
        // Проверка наличия игрока
        if (!game.getPlayerNames().containsKey(playerName)) {
            throw new IllegalArgumentException("Отсутствует игрок:" + playerName);
        }

        // Получаем игрока
        Player player = game.getPlayerNames().get(playerName);

        // Проверка, что текущий ход принадлежит правильному игроку
        if (!playerName.equals(game.getNextPlayerToAct())) {
            throw new IllegalStateException("Сейчас не ход игрока: " + playerName);
        }

        // Подсчет очков для хода
        int cost = PointsCalculator.costMovement(move);

        // Проверка валидности хода
        if (move.moveType() == Move.MoveType.ORDINARY) {
            if (ValidationMove.isValidOrdinaryMove(move, game.getField(), player)) {
                game.getAllGameMoves().add(move);
                move.makeMove(player);
            } else {
                throw new IllegalStateException("Недопустимый 'ORDINARY' ход: " + move);
            }
        } else if (move.moveType() == Move.MoveType.CAPTURE) {
            if (ValidationMove.isValidCaptureMove(move, player)) {
                game.getAllGameMoves().add(move);
                move.makeMove(player);
            } else {
                throw new IllegalStateException("Недопустимый 'CAPTURE' ход: " + move);
            }
        } else throw new IllegalStateException("Нет такого типа хода!");

        // Обновляем очки игрока
        player.decreaseTotalGamePoints(cost);

        // Переход хода к следующему игроку
        game.setNextPlayerToAct((game.getNextPlayerToActIndex() + 1) % NUM_PLAYERS);
    }

    @Override
    public void gameEnded(final String winner) {
        game.gameEnded(winner);
    }

    @Override
    public void endGameSession() {
        game.endGameSession();
    }

    public Game getGame() {
        return game;
    }

    /**
     * Паттерн абстрактная фабрика
     * Статический абстрактный класс
     */
    public static abstract class BotFactory {
        /**
         * Метод создания бота
         *
         * @param field поле, по которому ходит созданный бот
         * @return бота, который будет играть в игру
         */
        public abstract Bot createBot(final Field field);
    }
}
