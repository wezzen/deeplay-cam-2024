package io.deeplay.camp.engine.entities;

/**
 * Представление сущности ответ (response)
 */
public class Answer {
    Move move;
    String responseTime;

    @Override
    public String toString() {
        return "Answer{response='" + move + " : " + responseTime + "'}";
    }
}
