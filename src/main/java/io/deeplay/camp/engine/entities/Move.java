package io.deeplay.camp.engine.entities;

import io.deeplay.camp.engine.domain.MoveType;

import java.util.Arrays;
import java.util.Objects;

/*public abstract class Move {

    protected final int[] destinationCoordinate;
    protected final Fleet movedFleet;
    protected final boolean isFirstMove;

    protected Move(int[] destinationCoordinate, Fleet movedFleet) {
        this.destinationCoordinate = destinationCoordinate;
        this.movedFleet = movedFleet;
        this.isFirstMove = movedFleet.isFirstMove();
    }
    private Move(final int[] destinationCoordinate) {
        this.destinationCoordinate = destinationCoordinate;
        this.movedFleet = null;
        this.isFirstMove = false;
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
}*/
public record Move(int[] startPosition, int[] endPosition, MoveType moveType) {
    @Override
    public String toString() {
        return "Start position = " + Arrays.toString(startPosition) + " end position = " + Arrays.toString(endPosition);
    }
}
