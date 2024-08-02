package io.deeplay.camp.game.bots;

import io.deeplay.camp.game.entites.*;
import io.deeplay.camp.game.bots.RandomBot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.deeplay.camp.game.utils.PointsCalculator.DIAGONAL_COST;
import static io.deeplay.camp.game.utils.PointsCalculator.DIRECT_COST;
import static org.junit.jupiter.api.Assertions.*;

public class RandomBotTest {
    private Field field;
    private Player player;
    private RandomBot randomBot;
    private Fleet fleet;

    @BeforeEach
    public void setUp() {
        field = new Field(2); // Создаем поле размером 2x2
        player = new Player(0, "Player1");
        Player player_ = new Player(1, "Player2");
        Cell cellWithFleet = field.getBoard()[0][0];
        Cell cellWithFleet_ = field.getBoard()[1][0];
        fleet = new Fleet(cellWithFleet, player);
        Fleet fleet_ = new Fleet(cellWithFleet_, player_);
        Ship ship = new Ship(Ship.ShipType.BASIC, fleet);
        Ship ship_ = new Ship(Ship.ShipType.BASIC, fleet_);
        randomBot = new RandomBot.Factory().createBot("Player1", field);
        randomBot.connectingPlayer("Player1");
    }

    /*@Test
    public void testGetMove() {
        Move move1 = new Move(field.getBoard()[0][0], field.getBoard()[0][1], Move.MoveType.ORDINARY, DIRECT_COST);
        Move move1_ = new Move(field.getBoard()[0][0], field.getBoard()[0][1], Move.MoveType.CAPTURE, DIRECT_COST);
        Move move2 = new Move(field.getBoard()[0][0], field.getBoard()[1][0], Move.MoveType.ORDINARY, DIRECT_COST);
        Move move2_ = new Move(field.getBoard()[0][0], field.getBoard()[1][0], Move.MoveType.CAPTURE, DIRECT_COST);
        Move move3 = new Move(field.getBoard()[0][0], field.getBoard()[1][1], Move.MoveType.ORDINARY, DIAGONAL_COST);
        Move move3_ = new Move(field.getBoard()[0][0], field.getBoard()[1][1], Move.MoveType.CAPTURE, DIAGONAL_COST);

        List<Move> moves = new ArrayList<>();
        moves.add(move1);
        moves.add(move2);
        moves.add(move3);
        moves.add(move1_);
        moves.add(move2_);
        moves.add(move3_);

        Answer result = randomBot.getAnswer(field);

        assertTrue(moves.contains(result.getMove()));
        //todo нормальные тесты на бота после рефакторинга
//        assertThrows(RuntimeException.class, () -> randomBot.getAnswer(field));
    }*/
}
