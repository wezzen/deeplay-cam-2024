import io.deeplay.camp.game.entites.*;
import io.deeplay.camp.game.bots.RandomBot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RandomBotTest {
    private Field field;
    private Player player;
    private RandomBot randomBot;
    private Fleet fleet;

    @BeforeEach
    public void setUp() {
        field = new Field(2); // Создаем поле размером 2x2
        player = new Player(0,"Player1");
        Cell cellWithFleet = field.getBoard()[0][0];
        fleet = new Fleet(cellWithFleet, player);
        Ship ship = new Ship(Ship.ShipType.BASIC, fleet);
        randomBot = new RandomBot.Factory(player).createBot(field);
    }
/*
    @Test
    public void testGetMove() {
        Move move1 = new Move(field.getBoard()[0][0], field.getBoard()[0][1], Move.MoveType.ORDINARY, 5);
        Move move2 = new Move(field.getBoard()[0][0], field.getBoard()[1][0], Move.MoveType.CAPTURE, 5);
        Move move3 = new Move(field.getBoard()[0][0], field.getBoard()[1][1], Move.MoveType.CAPTURE, 7);

        List<Move> moves = new ArrayList<>();
        moves.add(move1);
        moves.add(move2);
        moves.add(move3);

        Move move_ = randomBot.getMove();

        assertTrue(moves.contains(move_));
        if (move_.moveType() == Move.MoveType.ORDINARY) {
            move_.makeMove(player);
        } else if (move_.moveType() == Move.MoveType.CAPTURE) {
            move_.makeAttack(player);
        }
    }*/
}
