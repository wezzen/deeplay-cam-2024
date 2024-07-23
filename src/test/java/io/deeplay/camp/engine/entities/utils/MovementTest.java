package io.deeplay.camp.engine.entities.utils;

import io.deeplay.camp.engine.entities.*;
import io.deeplay.camp.engine.entities.move.Move;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MovementTest {

    @Test
    void testValidPositionMove() {
        Field field = new Field(10);
        Move move = new Move(new Cell(5, 5, new Fleet(new ArrayList<>(), new Cell(5, 5))), new Cell(6, 6), Move.MoveType.ORDINARY);
        assertTrue(Movement.isValidMove(move, field));
    }

    @Test
    void testInvalidPositionMove() {
        Field field = new Field(10);
        Move move = new Move(new Cell(9, 9, new Fleet(new ArrayList<>(), new Cell(9, 9))), new Cell(10, 10), Move.MoveType.ORDINARY);
        assertFalse(Movement.isValidMove(move, field));
    }

    @Test
    void testInvalidAvailableMove() {
        Field field = new Field(10);
        Move move = new Move(new Cell(5, 5, new Fleet(new ArrayList<>(), new Cell(5, 5))), new Cell(6, 6, new Fleet(new ArrayList<>(), new Cell(6, 6))), Move.MoveType.ORDINARY);
        assertFalse(Movement.isValidMove(move, field));
    }

    @Test
    void testInvalidMoveType() {
        Field field = new Field(10);
        Move move = new Move(new Cell(5, 5, new Fleet(new ArrayList<>(), new Cell(5, 5))), new Cell(6, 6), Move.MoveType.CAPTURE);
        assertFalse(Movement.isValidMove(move, field));
    }

    @Test
    void testInvalidFleetPresent() {
        Field field = new Field(10);
        Move move = new Move(new Cell(5, 5), new Cell(6, 6), Move.MoveType.ORDINARY);
        assertFalse(Movement.isValidMove(move, field));
    }
}