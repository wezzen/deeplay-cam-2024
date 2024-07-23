package io.deeplay.camp.engine.interfaces;

import io.deeplay.camp.engine.domain.GalaxyListener;
import io.deeplay.camp.engine.domain.GameStates;
import io.deeplay.camp.engine.entities.Answer;

/**
 * Интерфейс для представления игрока, расширяет интерфейс игровых событий…
 */
public interface PlayerInterface extends GalaxyListener {
    /**
     * Получение ответа от игрока.
     *
     * @param state состояние игры
     * @return ответ игрока
     */
    Answer getAnswer(GameStates state);
}
