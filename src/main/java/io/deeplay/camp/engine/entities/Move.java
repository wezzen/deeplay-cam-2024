package io.deeplay.camp.engine.entities;

import java.util.Arrays;

/**
 * Класс - ход, как record
 * Чтобы не закладывать еще не продуманную логику.
 * Record обеспесчивает из коробки все требуемые методы (и даже больше)
 */
public record Move(Cell startPosition, Cell endPosition, MoveType moveType) {
    /**
     * Два типа хода:
     * ORDINARY - как перемещение по игровому полю
     * CAPTURE - атака на планету, захваченную противником
     */
    public enum MoveType {
        ORDINARY,
        CAPTURE
    }

    /**
     * Статус-флажок для контроля выполнения игроком хода:
     * DONE - выполнен
     * ILLEGAL_MOVE - ход не валидный, не выполнен
     * LEAVES_PLAYER_IN_CHECK - состояние проверки хода игрока
     */
    public enum MoveStatus {
        DONE {
            @Override
            public boolean isDone() {
                return true;
            }
        },
        ILLEGAL_MOVE {
            @Override
            public boolean isDone() {
                return false;
            }
        },
        LEAVES_PLAYER_IN_CHECK {
            @Override
            public boolean isDone() {
                return false;
            }
        };

        public abstract boolean isDone();
    }
    @Override
    public String toString() {
        return "Start position = " + startPosition.toString() + " end position = " + endPosition.toString();
    }
