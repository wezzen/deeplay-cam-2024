package io.deeplay.camp.engine.entities.domain;

/**
 * Интерфейс игровых событий
 */
public interface GameEvents {
    public void startGameSession(); //Инициализация игровой сессии
    public void connectingPlayers(); //Подключение игроков
    public void gameLobby(); //Лобби. Пока что добавил
    public void gameStarted(); //Начало игы
    public void makeMove(); //Событие хода
    public void gameEnded(); //Конец игры
    public void endGameSession(); //Заверешение игровой сессии

}
