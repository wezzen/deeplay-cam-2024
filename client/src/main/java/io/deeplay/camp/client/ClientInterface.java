package io.deeplay.camp.client;

import java.lang.reflect.Field;

public interface ClientInterface {

    void createGameSession(String username); // Создание игровой сессии

    void joinGameSession(String gameId); // Подключение к игровой сессии по gameId

    void requestPlayerMove(); // Запрос хода игрока

    void requestPlayerAttack(); // Запрос на атаку указанной цели

    void renderGameField(Field field); // Отрисовка состояния игрового поля

    void gameStateUpdate(); // Обновляет состояние игры

    void endGameSession(String gameId); // Завершение игровой сессии
}
