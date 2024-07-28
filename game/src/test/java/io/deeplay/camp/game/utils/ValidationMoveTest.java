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

    private Move createMove(Cell start, Cell end, int cost) {
        return new Move(start, end, Move.MoveType.ORDINARY, cost);
    }

    private Move createAttack(Cell start, Cell end, int cost) {
        return new Move(start, end, Move.MoveType.CAPTURE, cost);
    }


    private void setFleetAt(Cell cell) {
        Fleet fleet = new Fleet(cell, player);
        cell.setFleet(fleet);
    }

    @Test
    void testValidPositionMove() {
        Move move = createMove(field.getBoard()[0][0], field.getBoard()[2][2], 10);
        setFleetAt(field.getBoard()[0][0]);
        assertTrue(ValidationMove.isValidOrdinaryMove(move, field, player, 10));
    }

    @Test
    void testInvalidOwnerFleet() {
        Move move = createMove(field.getBoard()[0][0], field.getBoard()[2][2], 10);
        setFleetAt(field.getBoard()[0][0]);
        Player player2 = new Player(1, "1");
        assertFalse(ValidationMove.isValidOrdinaryMove(move, field, player2, 10));
    }

    @Test
    void testInvalidPositionMove() {
        Move move = createMove(new Cell(8, 8), new Cell(10, 10), 10);
        assertFalse(ValidationMove.isValidOrdinaryMove(move, field, player));
    }

    @Test
    void testInvalidFleetPresent() {
        Move move = createMove(new Cell(0, 0), new Cell(0, 0), 10);
        assertFalse(ValidationMove.isValidOrdinaryMove(move, field, player));
    }

    @Test
    void testInvalidCost(){
        Move move = createMove(field.getBoard()[0][0], field.getBoard()[2][2], 100);
        setFleetAt(field.getBoard()[0][0]);
        assertFalse(ValidationMove.isValidOrdinaryMove(move, field, player));
    }

    @Test
    void testValidCaptureMove() {
        Move move = createAttack(field.getBoard()[0][0], field.getBoard()[2][2], 10);
        setFleetAt(field.getBoard()[0][0]);
        Fleet fleet = new Fleet(field.getBoard()[2][2], new Player(1, "1"));
        field.getBoard()[2][2].setFleet(fleet);
        assertTrue(ValidationMove.isValidCaptureMove(move, player));
    }

    @Test
    void testInvalidCaptureMove1() {
        Move move = createAttack(field.getBoard()[0][0], field.getBoard()[2][2], 10);
        setFleetAt(field.getBoard()[0][0]);
        setFleetAt(field.getBoard()[2][2]);
        assertFalse(ValidationMove.isValidCaptureMove(move, player)); // на конечной точке флот атакующего игрока
    }

    @Test
    void testInvalidCaptureMove2() {
        Move move = createMove(field.getBoard()[0][0], field.getBoard()[2][2], 10); // неправильный тип хода
        setFleetAt(field.getBoard()[0][0]);
        Fleet fleet = new Fleet(field.getBoard()[2][2], new Player(1, "1"));
        field.getBoard()[2][2].setFleet(fleet);
        assertFalse(ValidationMove.isValidCaptureMove(move, player));
    }
    @Test
    void testInvalidCaptureMove3() {
        Move move = createAttack(field.getBoard()[0][0], field.getBoard()[0][0], 0);
        setFleetAt(field.getBoard()[0][0]);
        Fleet fleet = new Fleet(field.getBoard()[0][0], new Player(1, "1"));
        field.getBoard()[0][0].setFleet(fleet);
        assertFalse(ValidationMove.isValidCaptureMove(move, player)); // игрок атакует сам себя
    }

    @Test
    void testValidCaptureMove() {
        Move move = createAttack(field.getBoard()[0][0], field.getBoard()[2][2]);
        setFleetAt(field.getBoard()[0][0]);
        Fleet fleet = new Fleet(field.getBoard()[2][2], new Player(1, "1"));
        field.getBoard()[2][2].setFleet(fleet);
        assertTrue(ValidationMove.isValidCaptureMove(move, player, 10));
    }

    @Test
    void testInvalidCaptureMove1() {
        Move move = createAttack(field.getBoard()[0][0], field.getBoard()[2][2]);
        setFleetAt(field.getBoard()[0][0]);
        setFleetAt(field.getBoard()[2][2]);
        assertFalse(ValidationMove.isValidCaptureMove(move, player, 10)); // на конечной точке флот атакующего игрока
    }

    @Test
    void testInvalidCaptureMove2() {
        Move move = createMove(field.getBoard()[0][0], field.getBoard()[2][2]); // неправильный тип хода
        setFleetAt(field.getBoard()[0][0]);
        Fleet fleet = new Fleet(field.getBoard()[2][2], new Player(1, "1"));
        field.getBoard()[2][2].setFleet(fleet);
        assertFalse(ValidationMove.isValidCaptureMove(move, player, 10));
    }
    @Test
    void testInvalidCaptureMove3() {
        Move move = createAttack(field.getBoard()[0][0], field.getBoard()[0][0]);
        setFleetAt(field.getBoard()[0][0]);
        Fleet fleet = new Fleet(field.getBoard()[0][0], new Player(1, "1"));
        field.getBoard()[0][0].setFleet(fleet);
        assertFalse(ValidationMove.isValidCaptureMove(move, player, 10)); // игрок атакует сам себя
    }
}