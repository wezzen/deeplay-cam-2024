package io.deeplay.camp.engine.domain;

import io.deeplay.camp.engine.entities.Game;
import io.deeplay.camp.engine.entities.Player;

import java.util.List;

/**
 * Интерфейс игровых событий
 */
public interface GameEvents {
    /**
     * Инициализация игровой сессии
     * @param gameId создаем идентификатор сессии
     * @param gameType выбираем тип игры
     */
    public void startGameSession(String gameId, GameTypes gameType);

    /**
     * Подключаем игроков
     * @param waitingPlayer игрок на входе в игру
     */
    public void connectingPlayer(Player waitingPlayer);

    /**
     * Состояние ожидания для всех видов игроков, включая наблюдателей
     * @return список всех видов игроков, которых мы тянем в игру
     */
    public List<Player> gameLobby();

    /**
     * Начало игры
     * @return созданная игра
     */
    public Game gameStarted();

    /**
     * Любое игровое событие
     * @param game игра, которая происходит
     * @return флажок, для контроля завершенности игры
     */
    public boolean getPlayerAction(Game game);

    /**
     * Конец игры
     * @param winner Победитель в игре
     */
    public void gameEnded(Player winner);

    /**
     * Завершение игровой сессии
     */
    public void endGameSession();

}
