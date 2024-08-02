package io.deeplay.camp.game.utils;

import io.deeplay.camp.game.entites.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointsCalculatorTest {

    private Field field;
    private Fleet fleet;

    @BeforeEach
    void setUp() {
        field = new Field(5);
        Player player1 = new Player(0, "player1");
        fleet = new Fleet(field.getBoard()[0][0], player1);

    }

    @Test
    void testDirectMovement() {
        Cell startCell = field.getBoard()[0][0];
        Cell endCell = field.getBoard()[0][3];
        startCell.setFleet(fleet);
        Move move = new Move(startCell, endCell, Move.MoveType.ORDINARY, 0); // Прямое движение по 3Y
        int cost = PointsCalculator.costMovement(move);
        assertEquals(15, cost); // Стоимость прямого движения должна быть 15 (3 * 5)
    }

    @Test
    void testDiagonalMovement(){
        Cell startCell = field.getBoard()[0][0];
        Cell endCell = field.getBoard()[3][3];
        startCell.setFleet(fleet);
        Move move = new Move(startCell, endCell, Move.MoveType.ORDINARY, 0); // Диагональное движение по 3XY
        int cost = PointsCalculator.costMovement(move);
        assertEquals(21, cost); // Стоимость диагонального движения должно быть 21 (3 * 7)
    }

    @Test
    void testDirectDiagonalMovement(){
        Cell startCell = field.getBoard()[0][0];
        Cell endCell = field.getBoard()[2][3];
        startCell.setFleet(fleet);
        Move move = new Move(startCell, endCell, Move.MoveType.ORDINARY, 0); // Диагональное движение по 2XY + Y
        int cost = PointsCalculator.costMovement(move);
        assertEquals(19, cost); // Стоимость диагонального движения должно быть 19 (2 * 7 + 5)
    }

    @Test
    void testWithoutMovement() {
        Cell startCell = field.getBoard()[0][0];
        Cell endCell = field.getBoard()[0][0];
        startCell.setFleet(fleet);
        Move move = new Move(startCell, endCell, Move.MoveType.ORDINARY, 0);
        int cost = PointsCalculator.costMovement(move);
        assertEquals(0, cost); // Стоимость без движения должна быть 0
    }

}