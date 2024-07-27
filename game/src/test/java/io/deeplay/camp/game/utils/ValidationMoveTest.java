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


    private Move createMove(Cell start, Cell end) {
        return new Move(start, end, Move.MoveType.ORDINARY);

    }

    private void setFleetAt(Cell cell) {
        Fleet fleet = new Fleet(cell, player);
        cell.setFleet(fleet);
    }

    @Test
    void testValidPositionMove() {

        Move move = createMove(field.getBoard()[0][0], field.getBoard()[2][2]);
        setFleetAt(field.getBoard()[0][0]);
        assertTrue(ValidationMove.isValidOrdinaryMove(move, field, player, 10));

    }

    @Test
    void testInvalidOwnerFleet() {

        Move move = createMove(field.getBoard()[0][0], field.getBoard()[2][2]);
        setFleetAt(field.getBoard()[0][0]);
        Player player2 = new Player(1, "1");
        assertFalse(ValidationMove.isValidOrdinaryMove(move, field, player2, 10));

    }

    @Test
    void testInvalidPositionMove() {

        Move move = createMove(new Cell(8, 8), new Cell(10, 10));
        assertFalse(ValidationMove.isValidOrdinaryMove(move, field, player, 10));

    }

    @Test
    void testInvalidFleetPresent() {

        Move move = createMove(new Cell(0, 0), new Cell(0, 0));
        assertFalse(ValidationMove.isValidOrdinaryMove(move, field, player, 10));
    }

    @Test
    void testInvalidCost(){
        Move move = createMove(field.getBoard()[0][0], field.getBoard()[2][2]);
        setFleetAt(field.getBoard()[0][0]);
        assertFalse(ValidationMove.isValidOrdinaryMove(move, field, player, 60));
    }
}