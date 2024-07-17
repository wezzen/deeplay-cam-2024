package io.deeplay.camp.server;

public interface ServerInterface {
    void informGameCreated(String gameId); // Уведомление о создании игровой сессии

    void informPlayerJoined(String username); // Уведомление о подключении игрока к игровой сессии

    void processMoveRequest(); // обрабатывает запрос на перемещение игрока

    void processAttackRequest(); // обрабатывает запрос на перемещение игрока

    void informPlayerMove(String username); // Уведомление об очереди хода игрока

    void sendGameState(); // Отправка текущего состояния игрового поля

}
