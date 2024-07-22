package io.deeplay.camp.engine.entities;

/**
 * Представление сущности ответ игрока (response)
 */
public class Answer {
    Move move;
    String responseTime;

    public Answer(Move move) {
        this.move = move;
    }

    private void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    @Override
    public String toString() {
        return "Answer{response='" + move + " : " + responseTime + "'}";
    }
}
