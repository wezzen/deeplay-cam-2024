package io.deeplay.camp.engine.interfaces;

import io.deeplay.camp.engine.domain.GameEvents;

/**
 * Интерфейс для представления игрока, расширяет интерфейс игровых событий.
 *
 * @param <T> тип возвращаемого значения метода getAnswer
 */
public interface InterfacePlayer<T> extends GameEvents {
    /**
     * Получение ответа от игрока.
     *
     * @return ответ игрока
     */
    T getAnswer(GameEvents state);
}
