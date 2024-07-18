package io.deeplay.camp.moves;

import io.deeplay.camp.engine.entities.Cell;
import io.deeplay.camp.engine.entities.Field;
import io.deeplay.camp.engine.entities.Fleet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ActionsTest {
    private Fleet fleet;
    private Actions actions;

    @BeforeEach
    void setUp() {
        Field field = new Field(10);
        fleet = new Fleet(new ArrayList<>(), new Cell(1, 1));
        actions = new Actions(field, fleet);
    }

    @Test
    void testKeyPressedUp() {
        KeyEvent event = new KeyEvent(new JFrame(), KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_W, 'W');
        actions.keyPressed(event);
        assertEquals(new Cell(1, 2, fleet), fleet.getFleetPosition());
    }

    @Test
    void testKeyPressedDown() {
        KeyEvent event = new KeyEvent(new JFrame(), KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_S, 'S');
        actions.keyPressed(event);
        assertEquals(new Cell(1, 0, fleet), fleet.getFleetPosition());
    }

    @Test
    void testKeyPressedRight() {
        KeyEvent event = new KeyEvent(new JFrame(), KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_D, 'D');
        actions.keyPressed(event);
        assertEquals(new Cell(2, 1, fleet), fleet.getFleetPosition());
    }

    @Test
    void testKeyPressedLeft() {
        KeyEvent event = new KeyEvent(new JFrame(), KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_A, 'A');
        actions.keyPressed(event);
        assertEquals(new Cell(0, 1, fleet), fleet.getFleetPosition());
    }


}