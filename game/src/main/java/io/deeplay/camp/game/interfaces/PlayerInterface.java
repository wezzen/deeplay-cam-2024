package io.deeplay.camp.game.interfaces;

import io.deeplay.camp.game.domain.GalaxyListener;
import io.deeplay.camp.game.domain.GameStates;
import io.deeplay.camp.game.entites.Answer;

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
