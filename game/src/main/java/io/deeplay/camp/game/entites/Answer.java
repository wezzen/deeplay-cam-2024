package io.deeplay.camp.game.entites;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Представление сущности ответ игрока (response)
 */
public class Answer {
    private final Move move;
    private final String responseTime;

    /**
     * Конструктор, который инициализирует ответ с текущим временем в формате ISO_LOCAL_DATE_TIME.
     *
     * @param move действие игрока
     * @throws IllegalArgumentException если move является null
     */
    public Answer(final Move move) {
        this(move, LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

    /**
     * Конструктор, который инициализирует ответ с заданным временем.
     *
     * @param move         действие игрока
     * @param responseTime время ответа в формате строки
     * @throws IllegalArgumentException если move или responseTime являются null
     */
    public Answer(final Move move, final String responseTime) {
        if (move == null) {
            throw new IllegalArgumentException("Move cannot be null.");
        }
        if (responseTime == null) {
            throw new IllegalArgumentException("Response time cannot be null.");
        }
        this.move = move;
        this.responseTime = responseTime;
    }

    public Move getMove() {
        return move;
    }

    public String getResponseTime() {
        return responseTime;
    }

    @Override
    public String toString() {
        return "Answer{response='" + move + " : " + responseTime + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(move, answer.move) && Objects.equals(responseTime, answer.responseTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(move, responseTime);
    }
}
