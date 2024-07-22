package io.deeplay.camp.engine.entities.domain;

import io.deeplay.camp.engine.entities.*;


/**
 * Интерфейс игровых событий
 */
public interface GameEvents {
    /**
     * Инициализация игровой сессии
     *
     * @param game     игра
     * @param gameId   создаем идентификатор сессии
     * @param gameType выбираем тип игры
     */
    public void startGameSession(Game game, String gameId, GameTypes gameType);

    /**
     * Подключаем игроков
     *
     * @param game          игра
     * @param waitingPlayer игрок на входе в игру
     */
    public void connectingPlayer(Game game, Player waitingPlayer);

    /**
     * Начало игры
     *
     * @param game созданная игра
     */
    public void gameStarted(Game game);

    /**
     * Любое игровое событие
     *
     * @param game игра, которая происходит
     */
    public void getPlayerAction(Game game);

    /**
     * Конец игры
     *
     * @param game   игра
     * @param winner Победитель в игре
     */
    public void gameEnded(Game game, Player winner);

    /**
     * Завершение игровой сессии
     *
     * @param game игра
     */
    public void endGameSession(Game game);

}
