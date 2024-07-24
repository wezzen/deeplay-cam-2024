package io.deeplay.camp.game.bots;

import io.deeplay.camp.game.domain.GameTypes;
import io.deeplay.camp.game.entites.*;
import io.deeplay.camp.game.interfaces.PlayerInterface;

/**
 * Абстрактный класс Бот. В дальнейшем будет родителем
 * для разных ботов в игре.
 * <p>
 * Реализует интерфейс PlayerInterface, чтобы дать возможность
 * совершать боту ходы.
 */
public abstract class Bot implements PlayerInterface {
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
        return new Answer(getAction());
    }

    protected abstract Move getAction();

    @Override
    public void startGameSession(final String gameId, final GameTypes gameType) {

    }

    @Override
    public void connectingPlayer(final String waitingPlayerName) {

    }

    @Override
    public void gameStarted(final Field field) {

    }

    @Override
    public void getPlayerAction(final Move move, final String playerName) {

    }

    @Override
    public void gameEnded(final String winner) {

    }

    @Override
    public void endGameSession() {

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
