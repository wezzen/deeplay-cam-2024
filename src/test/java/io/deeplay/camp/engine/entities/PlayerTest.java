package io.deeplay.camp.engine.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {
    private Player player;
    private Fleet fleet1;
    private Fleet fleet2;
    private Planet planet1;
    private Planet planet2;
    private Move move1;
    private Move move2;
    private Cell cell;

    @BeforeEach
    public void setUp() {
        player = new Player(1L, "Player1");
        Ship ship = new Ship(Ship.ShipType.BASIC);
        Cell position = new Cell(0, 0);
        ArrayList<Ship> shipList = new ArrayList<>();

        fleet1 = new Fleet(shipList, position);
        fleet2 = new Fleet(shipList, position);

        planet1 = new Planet(10);
        planet2 = new Planet(20);

        cell = new Cell(1, 1);

        move1 = new Move(cell, cell, Move.MoveType.ORDINARY);
        move2 = new Move(cell, cell, Move.MoveType.ORDINARY);


        player.fleetList = new ArrayList<>(Arrays.asList(fleet1, fleet2));
        player.controlledPlanet = new ArrayList<>(Arrays.asList(planet1, planet2));
        player.legalMoves = new ArrayList<>(Arrays.asList(move1, move2));
    }

    @Test
    public void testPlayerConstructor() {
        Player player = new Player(1L, "Player1");
        assertEquals(1L, player.getId());
        assertEquals("Player1", player.getName());
        assertEquals(0, player.getTotalGamePoints());
    }

    @Test
    public void testGetFleetList() {
        assertEquals(Arrays.asList(fleet1, fleet2), player.getFleetList());
    }

    @Test
    public void testGetControlledPlanet() {
        assertEquals(Arrays.asList(planet1, planet2), player.getControlledPlanet());
    }

    @Test
    public void testGetLegalMoves() {
        assertEquals(Arrays.asList(move1, move2), player.getLegalMoves());
    }

    @Test
    public void testGetTotalGamePoints() {
        assertEquals(0, player.getTotalGamePoints());
    }

    @Test
    public void testGetCurrentGamePoints() {
        assertEquals(30, player.getCurrentGamePoints());
    }

    @Test
    public void testCalculateAttacksOnTile() {
        Collection<Move> moves = player.calculateAttacksOnTile(cell, Arrays.asList(move1, move2));
        assertEquals(Arrays.asList(move1, move2), moves);
        assertThrows(UnsupportedOperationException.class, () -> moves.add(new Move(cell, cell, Move.MoveType.ORDINARY)));

    }

    @Test
    public void testEquals() {
        Player player1 = new Player(1L, "Player1");
        Player player2 = new Player(1L, "Player1");
        assertEquals(player1, player2);
    }

    @Test
    public void testHashCode() {
        Player player1 = new Player(1L, "Player1");
        Player player2 = new Player(1L, "Player1");
        assertEquals(player1.hashCode(), player2.hashCode());
    }
    @Test
    public void testCalculateAttacksOnTile2() {
        Cell targetCell = new Cell(5, 5);
        Cell anotherCell = new Cell(3, 3);

        Move moveToTarget = new Move(new Cell(1, 1), targetCell, Move.MoveType.ORDINARY);
        Move moveToAnother = new Move(new Cell(2, 2), anotherCell, Move.MoveType.CAPTURE);
        Move anotherMoveToTarget = new Move(new Cell(4, 4), targetCell, Move.MoveType.ORDINARY);

        Collection<Move> haveMoves = new ArrayList<>();
        haveMoves.add(moveToTarget);
        haveMoves.add(moveToAnother);
        haveMoves.add(anotherMoveToTarget);

        player.legalMoves = haveMoves;

        Collection<Move> moves = player.calculateAttacksOnTile(targetCell, player.getLegalMoves());

        assertEquals(Arrays.asList(moveToTarget, anotherMoveToTarget), new ArrayList<>(moves));

        assertThrows(UnsupportedOperationException.class, () -> moves.add(new Move(targetCell, targetCell, Move.MoveType.ORDINARY)));
    }
}
