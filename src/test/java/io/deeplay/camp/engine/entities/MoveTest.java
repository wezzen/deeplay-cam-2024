package io.deeplay.camp.engine.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {
    private Cell startPositionSM;
    private Cell endPositionSM;
    private Move move;

    @BeforeEach
    public void setUp() {
        startPositionSM = new Cell(1, 1);
        endPositionSM = new Cell(5, 5);
        move = new Move(startPositionSM, endPositionSM, Move.MoveType.ORDINARY);
    }

    @Test
    public void testMoveCreation() {
        assertNotNull(move);
        assertEquals(startPositionSM, move.startPosition());
        assertEquals(endPositionSM, move.endPosition());
        assertEquals(Move.MoveType.ORDINARY, move.moveType());
    }

    @Test
    public void testToString() {
        String expectedString = "Start position = [1, 1] end position = [5, 5]";
        assertEquals(expectedString, move.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        Move sameMove = new Move(new Cell(1, 1), new Cell(5, 5), Move.MoveType.ORDINARY);
        Move differentMove = new Move(new Cell(2, 2), new Cell(5, 5), Move.MoveType.CAPTURE);

        assertEquals(move, sameMove);
        assertNotEquals(move, differentMove);

        assertEquals(move.hashCode(), sameMove.hashCode());
        assertNotEquals(move.hashCode(), differentMove.hashCode());
    }

    Cell startPosition = new Cell(0, 0);
    Cell toPosition = new Cell(1, 1);
    Cell endPosition = new Cell(2, 2);
    private final Move moveOrdinary = new Move(startPosition, toPosition, Move.MoveType.ORDINARY);
    private final Move moveCapture = new Move(toPosition, endPosition, Move.MoveType.CAPTURE);

    @Test
    void testToString1() {
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
