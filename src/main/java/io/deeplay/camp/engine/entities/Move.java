package io.deeplay.camp.engine.entities;

import java.util.Arrays;
import java.util.Objects;

public abstract class Move {
    protected final Field field;
    protected final int[] destinationCoordinate;
    protected final Fleet movedFleet;
    protected final boolean isFirstMove;

    protected Move(Field field, int[] destinationCoordinate, Fleet movedFleet) {
        this.field = field;
        this.destinationCoordinate = destinationCoordinate;
        this.movedFleet = movedFleet;
        this.isFirstMove = movedFleet.isFirstMove();
    }
    private Move(final Field field,
                 final int[] destinationCoordinate) {
        this.field = field;
        this.destinationCoordinate = destinationCoordinate;
        this.movedFleet = null;
        this.isFirstMove = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return isFirstMove == move.isFirstMove && Objects.equals(field, move.field) && Arrays.equals(destinationCoordinate, move.destinationCoordinate) && Objects.equals(movedFleet, move.movedFleet);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(field, movedFleet, isFirstMove);
        result = 31 * result + Arrays.hashCode(destinationCoordinate);
        return result;
    }

    public Field getField() { //Можно притянуть актуальное состоние поля отсюда
        return field;
    }

    public int[] getDestinationCoordinate() {
        return destinationCoordinate;
    }

    public int[] getCurrentCoordinate() {
        return this.movedFleet.getFleetPosition();
    }

    public Fleet getMovedFleet() {
        return movedFleet;
    }

    public boolean isFirstMove() {
        return isFirstMove;
    }

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

    //Тут все для некоторой moveFactory, категорий ходов разных, есди потребуется
}
