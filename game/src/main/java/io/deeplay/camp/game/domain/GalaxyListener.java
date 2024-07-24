package io.deeplay.camp.game.domain;


import io.deeplay.camp.game.entites.Field;
import io.deeplay.camp.game.entites.Move;

/**
 * Интерфейс игровых событий
 */
public interface GalaxyListener {
    /**
     * Инициализация игровой сессии
     *
     * @param gameId   создаем идентификатор сессии
     * @param gameType выбираем тип игры
     */
    void startGameSession(final String gameId, final GameTypes gameType);

    /**
     * Подключаем игроков
     *
     * @param waitingPlayerName игрок на входе в игру
     */
    void connectingPlayer(final String waitingPlayerName);

    /**
     * Начало игры
     */
    void gameStarted(final Field field);

    /**
     * Любое игровое событие
     *
     * @param move       ход
     * @param playerName игрок
     */
    void getPlayerAction(final Move move, final String playerName);

    /**
     * Конец игры
     *
     * @param winner Победитель в игре
     */
    void gameEnded(final String winner);

    /**
     * Завершение игровой сессии
     */
    void endGameSession();

}
