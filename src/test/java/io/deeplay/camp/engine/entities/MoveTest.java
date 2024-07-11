package io.deeplay.camp.engine.entities;

import io.deeplay.camp.engine.domain.MoveType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {
    Move moveOrdinary = new Move(new int []{0,0}, new int []{1, 1}, MoveType.ORDINARY);
    Move moveCapture = new Move(new int []{1,1}, new int []{2, 2}, MoveType.CAPTURE);
    @Test
    void testToString() {
        assertEquals("Start position = [0, 0] end position = [1, 1]", moveOrdinary.toString());
    }

    @Test
    void startPosition() {
        assertArrayEquals(new int[]{0, 0}, moveOrdinary.startPosition());
    }

    @Test
    void endPosition() {
        assertArrayEquals(new int[]{1, 1}, moveOrdinary.endPosition());
    }

    @Test
    void moveType() {
        assertEquals(MoveType.ORDINARY, moveOrdinary.moveType());
        assertEquals(MoveType.CAPTURE, moveCapture.moveType());
    }
}
