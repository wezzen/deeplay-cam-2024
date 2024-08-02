package io.deeplay.camp.game.entities;

import io.deeplay.camp.game.entites.Field;
import io.deeplay.camp.game.entites.Planet;
import io.deeplay.camp.game.entites.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    private Field field;
    private Player player1;
    private Player player2;

    @BeforeEach
    void setUp() {
        field = new Field(5);
        player1 = new Player(0, "player1");
        player2 = new Player(1, "player2");
        for (Planet planet : field.getPlanets()) {
            planet.setOwner(player1); // initially setting all planets to player1
        }
    }

    @Test
    void testFieldSize() {
        assertEquals(5, field.getSize(), "Field size = 5");
    }

    @Test
    void testFieldIsGenerated() {
        assertNotNull(field.getBoard(), "Field board not be null");
        assertEquals(5, field.getBoard().length, "Field board size = 5x5");
        assertEquals(5, field.getBoard()[0].length, "Field board size = 5x5");
    }

    @Test
    void testIsGameOver() {
        assertTrue(field.isGameOver(), "All planets should belong to player1, so the game should be over");

        // Change the owner of one planet
        field.getPlanets().get(0).setOwner(player2);

        assertFalse(field.isGameOver(), "Not all planets belong to the same player, so the game should not be over");
    }

    @Test
    void testIsWinner() {
        assertEquals(player1.getName(), field.isWinner());

        // Change the owner of one planet
        field.getPlanets().get(0).setOwner(player2);

    }
}
