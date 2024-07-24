package io.deeplay.camp.view;
import io.deeplay.camp.game.domain.GalaxyListener;
import io.deeplay.camp.game.entites.Field;
import io.deeplay.camp.game.entites.Move;

import java.util.List;

public interface GameUI extends GalaxyListener {
    /**
     * Создание игровой сессии
     */
    void createGameSession(String username);
    /**
     * Метод для выбора цвета кораблей
     */
    void selectShipColor(String color);
    /**
     * Метод для отрисовки игрового поля
     */
    void renderGameField(Field field);
    /**
     * Метод, который будет предлагать варианты хода
     */
    void suggestMoveOptions(List<Move> moves);
    /**
     * Метод для обновления состояния игры
     */
    void gameStateUpdate();
}
