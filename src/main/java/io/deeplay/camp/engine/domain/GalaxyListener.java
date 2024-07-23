package io.deeplay.camp.engine.domain;

import io.deeplay.camp.engine.entities.Field;
import io.deeplay.camp.engine.entities.Player;
import io.deeplay.camp.engine.entities.move.Move;


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
    public void startGameSession(String gameId, GameTypes gameType);

    /**
     * Подключаем игроков
     *
     * @param waitingPlayer игрок на входе в игру
     */
    public void connectingPlayer(Player waitingPlayer);

    /**
     * Начало игры
     */
    public void gameStarted(Field field);

    /**
     * Любое игровое событие
     *
     * @param move   ход
     * @param player игрок
     */
    public void getPlayerAction(Move move, Player player);

    /**
     * Конец игры
     *
     * @param winner Победитель в игре
     */
    public void gameEnded(Player winner);

    /**
     * Завершение игровой сессии
     */
    public void endGameSession();

}
