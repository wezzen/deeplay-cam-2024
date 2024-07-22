package io.deeplay.camp.engine.interfaces;

import io.deeplay.camp.engine.domain.GameEvents;

/**
 * Интерфейс для представления игрока, расширяет интерфейс игровых событий.
 */
public interface InterfacePlayer extends GameEvents {
    /**
     * Получение ответа от игрока.
     * @param state событие в игре
     * @return ответ игрока
     */
     Answer getAnswer(GameEvents state);
}
