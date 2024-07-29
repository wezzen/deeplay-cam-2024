package io.deeplay.camp.game.entities;

import io.deeplay.camp.game.entites.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {
    private Player player;
    private Field field;


    @BeforeEach
    public void setUp() {
        player = new Player(1L, "Player1");

    }

    @Test
    public void testPlayerConstructor() {
        Player player = new Player(1, "Player1");
        assertEquals(1, player.id);
        assertEquals("Player1", player.getName());
    }

    //
//    @Test
//    public void testGetControlledPlanet() {
//        assertEquals(Arrays.asList(planet1, planet2), player.getControlledPlanet());
//    }
//
//    @Test
//    public void testGetLegalMoves() {
//        assertEquals(Arrays.asList(move1, move2), player.getLegalMoves());
//    }
//
//    @Test
//    public void testGetTotalGamePoints() {
//        assertEquals(0, player.getTotalGamePoints());
//    }
//
//    @Test
//    public void testGetCurrentGamePoints() {
//        assertEquals(30, player.getCurrentGamePoints());
//    }
//
//    @Test
//    public void testCalculateAttacksOnTile() {
//        Collection<Move> moves = player.calculateAttacksOnTile(cell, Arrays.asList(move1, move2));
//        assertEquals(Arrays.asList(move1, move2), moves);
//        assertThrows(UnsupportedOperationException.class, () -> moves.add(new Move(cell, cell, Move.MoveType.ORDINARY)));
//
//    }
//
//    @Test
//    public void testCalculateAttacksOnTile2() {
//        Cell targetCell = new Cell(5, 5);
//        Cell anotherCell = new Cell(3, 3);
//
//        Move moveToTarget = new Move(new Cell(1, 1), targetCell, Move.MoveType.ORDINARY);
//        Move moveToAnother = new Move(new Cell(2, 2), anotherCell, Move.MoveType.CAPTURE);
//        Move anotherMoveToTarget = new Move(new Cell(4, 4), targetCell, Move.MoveType.ORDINARY);
//
//        Collection<Move> haveMoves = new ArrayList<>();
//        haveMoves.add(moveToTarget);
//        haveMoves.add(moveToAnother);
//        haveMoves.add(anotherMoveToTarget);
//
//        player.legalMoves = haveMoves;
//
//        Collection<Move> moves = player.calculateAttacksOnTile(targetCell, player.getLegalMoves());
//
//        assertEquals(Arrays.asList(moveToTarget, anotherMoveToTarget), new ArrayList<>(moves));
//
//        assertThrows(UnsupportedOperationException.class, () -> moves.add(new Move(targetCell, targetCell, Move.MoveType.ORDINARY)));
//    }
    @Test
    void testAddLegalMoves() {
        field = new Field(5);
        player.addLegalMoves(field.getBoard()[2][2], field);
        assertEquals(field.getSize() * field.getSize() - 1, player.legalMoves.size());
    }
    @Test
    void testAddLegalMovesValidMoves() {
        field = new Field(5);
        player.addLegalMoves(field.getBoard()[2][2], field);
        Move move1 = new Move(field.getBoard()[2][2], field.getBoard()[1][1], Move.MoveType.ORDINARY, 7);
        Move move2 = new Move(field.getBoard()[2][2], field.getBoard()[2][1], Move.MoveType.ORDINARY, 5);
        Move move3 = new Move(field.getBoard()[2][2], field.getBoard()[0][0], Move.MoveType.ORDINARY, 14);
        assertTrue(player.legalMoves.contains(move1));
        assertTrue(player.legalMoves.contains(move2));
        assertTrue(player.legalMoves.contains(move3));
    }
    @Test
    void testAddLegalMovesInvalidMoves() {
        Field newField = new Field(20);
        player.addLegalMoves(newField.getBoard()[2][2], newField);
        Move move1 = new Move(newField.getBoard()[2][2], newField.getBoard()[9][10], Move.MoveType.ORDINARY, 54); // уже не хватает очков для хода
        Move move2 = new Move(newField.getBoard()[2][2], newField.getBoard()[9][9], Move.MoveType.ORDINARY, 49);
        assertFalse(player.legalMoves.contains(move1));
        assertTrue(player.legalMoves.contains(move2));
    }
}
