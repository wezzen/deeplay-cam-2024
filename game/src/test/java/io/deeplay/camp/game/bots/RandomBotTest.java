package io.deeplay.camp.game.bots;

import io.deeplay.camp.game.entites.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.deeplay.camp.game.utils.PointsCalculator.DIAGONAL_COST;
import static io.deeplay.camp.game.utils.PointsCalculator.DIRECT_COST;
import static org.junit.jupiter.api.Assertions.*;

public class RandomBotTest {
    private Field field;
    private RandomBot randomBot;

    @BeforeEach
    public void setUp() {
        field = new Field(2); // Создаем поле размером 2x2

        randomBot = new RandomBot.Factory().createBot("Player1", field);
        randomBot.connectingPlayer("Player1");
        randomBot.connectingPlayer("Player2");

        randomBot.startGameSession(randomBot.game.getId(), randomBot.game.getGameType());

        // Устанавливаем начальные позиции
        randomBot.gameStarted(field);

        // Создаем флоты
        randomBot.createShips(List.of(Ship.ShipType.BASIC), "Player1");
        randomBot.createShips(List.of(Ship.ShipType.MEDIUM), "Player2");
    }

    @Test
    public void testGetMove() {
        Move move1 = new Move(field.getBoard()[0][0], field.getBoard()[0][1], Move.MoveType.ORDINARY, DIRECT_COST + 1);
        Move move1_ = new Move(field.getBoard()[0][0], field.getBoard()[0][1], Move.MoveType.CAPTURE, DIRECT_COST);
        Move move2 = new Move(field.getBoard()[0][0], field.getBoard()[1][0], Move.MoveType.ORDINARY, DIRECT_COST + 1);
        Move move2_ = new Move(field.getBoard()[0][0], field.getBoard()[1][0], Move.MoveType.CAPTURE, DIRECT_COST);
        Move move3 = new Move(field.getBoard()[0][0], field.getBoard()[1][1], Move.MoveType.ORDINARY, DIAGONAL_COST + 1);
        Move move3_ = new Move(field.getBoard()[0][0], field.getBoard()[1][1], Move.MoveType.CAPTURE, DIAGONAL_COST);

        List<Move> moves = new ArrayList<>();
        moves.add(move1);
        moves.add(move2);
        moves.add(move3);
        moves.add(move1_);
        moves.add(move2_);
        moves.add(move3_);

        Answer result = randomBot.getAnswer(randomBot.game.getField());
        randomBot.getPlayerAction(result.getMove(), "Player1");

        assertTrue(randomBot.game.getAllGameMoves().contains(result.getMove()));
        //todo нормальные тесты на бота после рефакторинга
    }
}
