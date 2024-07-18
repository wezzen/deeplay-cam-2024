package io.deeplay.camp.engine.entities.domain;

import io.deeplay.camp.engine.entities.*;

/**
 * Интерфейс игровых событий
 */
public interface GameEvents {
    public Game startGameSession(String gameId, GameTypes gameType); //Инициализация игровой сессии

    public Player connectingPlayer(Player waitingPlayer); //Подключение игроков

    public void gameLobby(); //Лобби. Пока что добавил

    public void gameStarted(); //Начало игы

    public Move makeMove(Player player, Fleet fleet, Field field); //Событие хода

    public void gameEnded(); //Конец игры

    public Game endGameSession(Player winer); //Завершение игровой сессии

}
