package io.deeplay.camp.game.interfaces;

import io.deeplay.camp.game.domain.GalaxyListener;
import io.deeplay.camp.game.entites.Answer;

/**
 * Интерфейс для представления игрока, расширяет интерфейс игровых событий…
 */
public interface PlayerInterface extends GalaxyListener {
    /**
     * Получение ответа от игрока.
     *
     * @return ответ игрока
     */
    Answer getAnswer();
}
