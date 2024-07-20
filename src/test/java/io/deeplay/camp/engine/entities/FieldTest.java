package io.deeplay.camp.engine.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FieldTest {

    private Field field;

    @BeforeEach
    void setUp() {
        field = new Field(5);
    }

    @Test
    void testFieldSize() {
        assertEquals(5, field.getSize(), "Field size = 5");
    }

    @Test
    void testFieldIsGenerated() {
        assertNotNull(field.getBoard(), "Field board not be null");
        assertEquals(5, field.getBoard().length, "Field board size = 5x5");
        assertEquals(5, field.getBoard()[0].length, "Field board size = 5x5");
    }

    @Test
    void testFieldSymmetry() {
        for (int i = 0; i < field.getSize(); i++) {
            for (int j = 0; j < i; j++) {
                assertEquals(field.getBoard()[i][j].planet, field.getBoard()[j][i].planet);
            }
        }
    }

    @Test
    void testUpdateFieldMovesFleet() {
        Cell start = new Cell(0, 0);
        Fleet fleet = new Fleet(new ArrayList<>(), start);
        start.setFleet(fleet);
        Cell end = new Cell(1, 1);
        Move move = new Move(start, end, Move.MoveType.ORDINARY);
        field.updateField(move);
        assertNull(start.getFleet());
        assertEquals(fleet, end.getFleet());
        assertEquals(end, fleet.getFleetPosition());
    }
}
