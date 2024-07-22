package io.deeplay.camp.engine.interfaces;

/**
 * Класс, представляющий ответ игрока на событие
 */
public record Answer(Object response) {

    @Override
    public String toString() {
        return "Answer{response='" + response + "'}";
    }
}
