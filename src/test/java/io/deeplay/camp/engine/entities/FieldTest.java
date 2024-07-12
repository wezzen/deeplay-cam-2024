package io.deeplay.camp.engine.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        assertNotNull(field.board, "Field board not be null");
        assertEquals(5, field.board.length, "Field board size = 5x5");
        assertEquals(5, field.board[0].length, "Field board size = 5x5");
    }
}
