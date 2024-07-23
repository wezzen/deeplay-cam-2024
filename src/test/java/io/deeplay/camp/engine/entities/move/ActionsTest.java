package io.deeplay.camp.engine.entities.move;

import io.deeplay.camp.engine.entities.Field;
import io.deeplay.camp.engine.entities.Fleet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ActionsTest {
    private Field field;

    @BeforeEach
    public void setUp() {
        // Инициализация поля для тестов
        field = new Field(5); // Предположим, что у вас есть конструктор по умолчанию
    }

    @Test
    public void testValidMoves() {
        List<Move> moves = List.of(new Move(field.getBoard()[0][0], field.getBoard()[0][1], Move.MoveType.ORDINARY), new Move(field.getBoard()[0][1], field.getBoard()[0][1], Move.MoveType.ORDINARY), new Move(field.getBoard()[0][2], field.getBoard()[0][3], Move.MoveType.ORDINARY), new Move(field.getBoard()[0][3], field.getBoard()[1][4], Move.MoveType.ORDINARY));
        field.getBoard()[0][0].setFleet(new Fleet(new ArrayList<>(), field.getBoard()[0][0]));

        Actions actions = new Actions(moves, field);
        List<Move> legalMoves = actions.getLegalMoves();
        assertEquals(1, legalMoves.size());
    }

}