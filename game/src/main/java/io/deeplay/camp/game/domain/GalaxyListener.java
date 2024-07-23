package io.deeplay.camp.game.domain;


import io.deeplay.camp.game.entites.Field;
import io.deeplay.camp.game.entites.Move;
import io.deeplay.camp.game.entites.Player;

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
    void startGameSession(String gameId, GameTypes gameType);

    /**
     * Подключаем игроков
     *
     * @param waitingPlayerName игрок на входе в игру
     */
    void connectingPlayer(String waitingPlayerName);

    /**
     * Начало игры
     */
    void gameStarted(Field field, String firstPlayerName);

    /**
     * Любое игровое событие
     *
     * @param move       ход
     * @param playerName игрок
     */
    void getPlayerAction(Move move, String playerName);

    /**
     * Конец игры
     *
     * @param winner Победитель в игре
     */
    void gameEnded(String winner);

    /**
     * Завершение игровой сессии
     */
    void endGameSession();

}
