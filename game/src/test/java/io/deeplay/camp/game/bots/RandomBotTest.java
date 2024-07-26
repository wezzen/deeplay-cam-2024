package io.deeplay.camp.game.bots;

import io.deeplay.camp.game.entites.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RandomBotTest {

    private Field field;
    private Player player;
    private RandomBot bot;

    @BeforeEach
    void setUp() {
        int size = 5;
        field = new Field(size);
        player = new Player(1, "TestPlayer");
        Cell position = field.getBoard()[0][0];
        // Инициализация флота и добавление его в ячейку
        Fleet fleet = new Fleet(position, player);
        Ship ship = new Ship(Ship.ShipType.BASIC, fleet);

        bot = new RandomBot(field, player);
    }

    @Test
    void testGetMove() {
        Move move = bot.getMove();
        assertNotNull(move, "Move should not be null");
        assertNotNull(move.startPosition(), "Start position should not be null");
        assertNotNull(move.endPosition(), "End position should not be null");

        Cell startCell = move.startPosition();
        Cell endCell = move.endPosition();
        assertTrue(startCell.x >= 0 && startCell.x < field.getBoard().length, "Start cell row should be within board range");
        assertTrue(startCell.y >= 0 && startCell.y < field.getBoard()[0].length, "Start cell column should be within board range");
        assertTrue(endCell.x >= 0 && endCell.x < field.getBoard().length, "End cell row should be within board range");
        assertTrue(endCell.y >= 0 && endCell.y < field.getBoard()[0].length, "End cell column should be within board range");
    }

    @Test
    void testMoveType() {
        Move move = bot.getMove();
        assertEquals(Move.MoveType.ORDINARY, move.moveType(), "Move type should be ORDINARY");
    }

    @Test
    void testMakeMove() {
        int initialFleetCount = player.getFleetList().size();
        Move move = bot.getMove();
        // Проверяем, что флот перемещен корректно
        if (move.startPosition().getFleet() != null) {
            assertNotNull(move.endPosition().getFleet(), "End position should have a fleet after the move");
            assertNull(move.startPosition().getFleet(), "Start position should be empty after the move");
        }
        // Проверяем изменение списка флотов игрока, если флот уничтожен или перемещен
        int finalFleetCount = player.getFleetList().size();
        assertTrue(finalFleetCount <= initialFleetCount, "Final fleet count should not exceed the initial count");
    }

    @Test
    void testGetRandomCell() {
        Cell[][] board = field.getBoard();
        Cell randomCell = bot.getRandomCell(board);
        assertNotNull(randomCell, "Random cell should not be null");
        assertTrue(randomCell.x >= 0 && randomCell.x < board.length, "Random cell row should be within board range");
        assertTrue(randomCell.y >= 0 && randomCell.y < board[0].length, "Random cell column should be within board range");
    }
}
