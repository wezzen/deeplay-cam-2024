package io.deeplay.camp.engine.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {
    Cell startPosition = new Cell(0, 0);
    Cell toPosition = new Cell(1, 1);
    Cell endPosition = new Cell(2, 2);
    private final Move moveOrdinary = new Move(startPosition, toPosition, Move.MoveType.ORDINARY);
    private final Move moveCapture = new Move(toPosition, endPosition, Move.MoveType.CAPTURE);
    @Test
    void testToString() {
        assertEquals("Start position = [0, 0] end position = [1, 1]", moveOrdinary.toString());
    }

    @Test
    void startPosition() {
        assertEquals(startPosition, moveOrdinary.startPosition());
    }

    @Test
    void endPosition() {
        assertEquals(toPosition, moveOrdinary.endPosition());
    }

    @Test
    void moveType() {
        assertEquals(Move.MoveType.ORDINARY, moveOrdinary.moveType());
        assertEquals(Move.MoveType.CAPTURE, moveCapture.moveType());
    }
}
