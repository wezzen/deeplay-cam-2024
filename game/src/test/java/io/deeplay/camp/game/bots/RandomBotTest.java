package io.deeplay.camp.game.bots;

import io.deeplay.camp.game.entites.Field;
import io.deeplay.camp.game.entites.Move;
import io.deeplay.camp.game.entites.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RandomBotTest {

    private Field field;
    private RandomBot randomBot;

    @BeforeEach
    public void setUp() {
        field = new Field(8); // Например, создаём поле размером 8x8
        randomBot = new RandomBot.Factory().createBot(field);
    }

    @Test
    public void testGetAction() {
        Move expectedMove = new Move(new Cell(0, 0), new Cell(1, 1), Move.MoveType.ORDINARY);
        Move move = randomBot.getAction();
        assertNotNull(move);
        assertEquals(expectedMove, move);
    }
}
