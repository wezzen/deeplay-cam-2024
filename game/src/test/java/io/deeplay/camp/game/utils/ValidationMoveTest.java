package io.deeplay.camp.game.utils;

import io.deeplay.camp.game.entites.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationMoveTest {

    private Field field;
    private Player player;

    @BeforeEach
    void setUp() {
        field = new Field(10);
        player = new Player(0, "0");
    }

    private Move createMove(Cell start, Cell end, Move.MoveType moveType) {
        return new Move(start, end, moveType);
    }

    private void setFleetAt(Cell cell) {
        Fleet fleet = new Fleet(cell, player);
        cell.setFleet(fleet);
    }

    @Test
    void testValidPositionMove() {
        Move move = createMove(field.getBoard()[0][0], field.getBoard()[2][2], Move.MoveType.ORDINARY);
        setFleetAt(field.getBoard()[0][0]);
        assertTrue(ValidationMove.isValidOrdinaryMove(move, field, player));
    }

    @Test
    void testInvalidOwnerFleet() {
        Move move = createMove(field.getBoard()[0][0], field.getBoard()[2][2], Move.MoveType.ORDINARY);
        setFleetAt(field.getBoard()[0][0]);
        Player player2 = new Player(1, "1");
        assertFalse(ValidationMove.isValidOrdinaryMove(move, field, player2));
    }

    @Test
    void testInvalidPositionMove() {
        Move move = createMove(new Cell(8, 8), new Cell(10, 10), Move.MoveType.ORDINARY);
        assertFalse(ValidationMove.isValidOrdinaryMove(move, field, player));
    }

    @Test
    void testInvalidFleetPresent() {
        Move move = createMove(new Cell(0, 0), new Cell(0, 0), Move.MoveType.ORDINARY);
        assertFalse(ValidationMove.isValidOrdinaryMove(move, field, player));
    }
}