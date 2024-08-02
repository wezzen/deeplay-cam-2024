package io.deeplay.camp.game.entities;

import io.deeplay.camp.game.entites.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MoveTest {
    private Cell startPositionSM;
    private Cell endPositionSM;
    private Move move;

    @BeforeEach
    public void setUp() {
        startPositionSM = new Cell(1, 1);
        endPositionSM = new Cell(5, 5);
        move = new Move(startPositionSM, endPositionSM, Move.MoveType.ORDINARY, 5);
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
        Move sameMove = new Move(new Cell(1, 1), new Cell(5, 5), Move.MoveType.ORDINARY, 5);
        Move differentMove = new Move(new Cell(2, 2), new Cell(5, 5), Move.MoveType.CAPTURE, 5);

        assertEquals(move, sameMove);
        assertNotEquals(move, differentMove);

        assertEquals(move.hashCode(), sameMove.hashCode());
        assertNotEquals(move.hashCode(), differentMove.hashCode());
    }

    Cell startPosition = new Cell(0, 0);
    Cell toPosition = new Cell(1, 1);
    Cell endPosition = new Cell(2, 2);
    private final Move moveOrdinary = new Move(startPosition, toPosition, Move.MoveType.ORDINARY, 5);
    private final Move moveCapture = new Move(toPosition, endPosition, Move.MoveType.CAPTURE, 5);

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

    @Test
    void testMoveToEmptyCell() {
        Field field = new Field(10);
        Move move = new Move(field.getBoard()[0][0], field.getBoard()[2][2], Move.MoveType.ORDINARY, 5);
        Player player = new Player(0, "0");
        Fleet fleet = new Fleet(field.getBoard()[0][0], player);
        field.getBoard()[0][0].setFleet(fleet);
        move.makeMove(player);
        assertNull(field.getBoard()[0][0].getFleet());
        assertNotNull(field.getBoard()[2][2].getFleet());
        assertEquals(field.getBoard()[2][2].getFleet(), fleet);

    }

    @Test
    void testMoveToStrongEnemyFleet() {
        Field field = new Field(10);
        Move move = new Move(field.getBoard()[0][0], field.getBoard()[2][2], Move.MoveType.ORDINARY, 5);
        Player player1 = new Player(0, "0");
        Player player2 = new Player(1, "1");
        Fleet fleet1 = new Fleet(field.getBoard()[0][0], player1);
        Fleet fleet2 = new Fleet(field.getBoard()[2][2], player2);
        field.getBoard()[0][0].setFleet(fleet1);
        field.getBoard()[2][2].setFleet(fleet2);
        move.makeMove(player1);
        assertNull(field.getBoard()[0][0].getFleet());
        assertNotNull(field.getBoard()[2][2].getFleet());
        assertTrue(player2.getFleetList().contains(fleet2)); // по нашему методу сражения флотов, при одинаковой силе флотов, побеждает враг
        assertFalse(player1.getFleetList().contains(fleet1));
    }

    @Test
    void testMoveToWeakEnemyFleet() {
        Field field = new Field(10);
        Move move = new Move(field.getBoard()[0][0], field.getBoard()[2][2], Move.MoveType.ORDINARY, 5);
        Player player1 = new Player(0, "0");
        Player player2 = new Player(1, "1");
        Fleet fleet1 = new Fleet(field.getBoard()[0][0], player1);
        Fleet fleet2 = new Fleet(field.getBoard()[2][2], player2);
        new Ship(Ship.ShipType.MEDIUM, fleet1);
        move.makeMove(player1);
        assertNull(field.getBoard()[0][0].getFleet());
        assertNotNull(field.getBoard()[2][2].getFleet());
        assertFalse(player2.getFleetList().contains(fleet2)); // у плеер1 сила больше, поэтому у плеера2 флот уничтожается
        assertTrue(player1.getFleetList().contains(fleet1));
    }

    @Test
    void testMoveToJoinFleet() {
        Field field = new Field(10);
        Move move = new Move(field.getBoard()[0][0], field.getBoard()[2][2], Move.MoveType.ORDINARY, 5);
        Player player = new Player(0, "0");
        Fleet fleet1 = new Fleet(field.getBoard()[0][0], player);
        Fleet fleet2 = new Fleet(field.getBoard()[2][2], player);
        move.makeMove(player);
        assertNull(field.getBoard()[0][0].getFleet());
        assertNotNull(field.getBoard()[2][2].getFleet());
        assertFalse(player.getFleetList().contains(fleet1)); // у плеера уже хранится новый флот, состоящий из двух флотов
    }

    @Test
    void testAttackFleet() {
        Field field = new Field(10);
        Move move = new Move(field.getBoard()[1][1], field.getBoard()[3][2], Move.MoveType.CAPTURE, 10);
        Player player1 = new Player(0, "0");
        Player player2 = new Player(1, "1");
        Fleet fleet1 = new Fleet(field.getBoard()[1][1], player1);
        Fleet fleet2 = new Fleet(field.getBoard()[3][2], player2);
        field.getBoard()[1][1].setFleet(fleet1);
        field.getBoard()[3][2].setFleet(fleet2);
        new Ship(Ship.ShipType.MEDIUM, fleet1);
        move.makeAttack(player1);
        assertNull(field.getBoard()[3][2].getFleet()); // у оппонента флот слабее, поэтому он уничтожается
        assertNotNull(field.getBoard()[1][1].getFleet()); // в отличие от ORDINARY ход CAPTURE флот остается на месте
        assertFalse(player2.getFleetList().contains(fleet2));
        assertTrue(player1.getFleetList().contains(fleet1));
    }

    @Test
    void testCapturePlanet() {
        Player player = new Player(0, "0");
        Planet planet = new Planet(150);
        move.capturePlanet(player, planet);
        assertTrue(planet.isCaptured());
        assertTrue(player.controlledPlanet.contains(planet));
    }

    @Test
    void testMovePlanet() {
        Player player = new Player(0, "0");
        Field field = new Field(5);
        new Ship(Ship.ShipType.POWERFUL, new Fleet(field.getBoard()[1][1], player));
        Move move = new Move(field.getBoard()[1][1], field.getBoard()[3][2], Move.MoveType.CAPTURE, 10);
        move.makeMove(player);
        if (field.getBoard()[3][2].planet != null && field.getBoard()[3][2].planet.points <= 200) {
            assertTrue(field.getBoard()[3][2].planet.isCaptured());
            assertTrue(player.controlledPlanet.contains(field.getBoard()[3][2].planet));
        }
    }
    @Test
    void testAttackPlanet() {
        Player player = new Player(0, "0");
        Field field = new Field(5);
        new Ship(Ship.ShipType.POWERFUL, new Fleet(field.getBoard()[1][1], player));
        Move move1 = new Move(field.getBoard()[1][1], field.getBoard()[3][2], Move.MoveType.CAPTURE, 10);
        move1.makeAttack(player);
        if (field.getBoard()[3][2].planet != null && field.getBoard()[3][2].planet.points <= 200) {
            assertTrue(field.getBoard()[3][2].planet.isCaptured());
            assertTrue(player.controlledPlanet.contains(field.getBoard()[3][2].planet));
        }
        Move move2 = new Move(field.getBoard()[1][1], field.getBoard()[4][4], Move.MoveType.CAPTURE, 10);
        move2.makeAttack(player);
        if (field.getBoard()[3][2].planet != null && field.getBoard()[3][2].planet.points <= 200) {
            assertTrue(field.getBoard()[3][2].planet.isCaptured());
            assertTrue(player.controlledPlanet.contains(field.getBoard()[3][2].planet));
        }
    }

}
