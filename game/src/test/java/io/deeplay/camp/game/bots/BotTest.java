package io.deeplay.camp.game.bots;

import io.deeplay.camp.game.domain.GameTypes;
import io.deeplay.camp.game.entites.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BotTest {
    private static class TestBot extends Bot {
        protected TestBot(final Field field) {
            super(field);
        }

        public static class Factory extends BotFactory {
            private final Player player;

            public Factory(Player player) {
                this.player = player;
            }

            @Override
            public TestBot createBot(final Field field) {
                return new TestBot(field);
            }
        }

        @Override
        protected Move getMove() {
            // Возвращаем тестовый ход для простоты
            return new Move(new Cell(0, 0), new Cell(1, 1), Move.MoveType.ORDINARY, 7);
        }
    }

    private Field field;
    private Bot bot;
    Player player = new Player(0,"player1");

    @BeforeEach
    void setUp() {
        field = new Field(10);
        bot = new TestBot.Factory(player).createBot(field);
    }

    @Test
    void testStartGameSession() {
        assertDoesNotThrow(() -> bot.startGameSession("testGameId", GameTypes.HumanVsBot));
    }

    @Test
    void testConnectingPlayer() {
        assertDoesNotThrow(() -> bot.connectingPlayer("player1"));
    }

    @Test
    void testGameStarted() {
        assertDoesNotThrow(() -> bot.gameStarted(field));
    }

    @Test
    void testGetPlayerAction_ValidOrdinaryMove() {
        Player player_ = new Player(1,"player2");
        Fleet fleet = new Fleet(field.getBoard()[0][0], player);
        Fleet fleet_ = new Fleet(field.getBoard()[0][1], player_);
        Ship ship = new Ship(Ship.ShipType.BASIC, fleet);
        Ship ship_ = new Ship(Ship.ShipType.BASIC, fleet_);
        Move move = new Move(field.getBoard()[0][0], field.getBoard()[1][1], Move.MoveType.ORDINARY, 7);
        //bot.getGame().getPlayerNames().put("player1", player);
        //bot.getGame().getPlayerNames().put("player2", player_);
        bot.getGame().connectingPlayer(player.getName());
        bot.getGame().connectingPlayer(player_.getName());
        bot.getGame().gameStarted(field);
        bot.getGame().getPlayerNames().get("player1").fleetList.add(fleet); //Придумать, что с этим делать
        bot.getGame().getPlayerNames().get("player2").fleetList.add(fleet_);
        //bot.getGame().setNextPlayerToAct(0);
        //move.makeMove(bot.getGame().getPlayerNames().get("player1"));
        bot.getGame().setNextPlayerToAct(0);

        assertDoesNotThrow(() -> bot.getPlayerAction(move, "player1"));
    }

    @Test
    void testGetPlayerAction_InvalidPlayer() {
        Move move = new Move(new Cell(0, 0), new Cell(1, 1), Move.MoveType.ORDINARY, 7);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> bot.getPlayerAction(move, "invalidPlayer"));
        assertEquals("Отсутствует игрок:invalidPlayer", exception.getMessage());
    }

    @Test
    void testGetPlayerAction_InvalidTurn() {
        Player player_ = new Player(1, "player2");
        Move move = new Move(new Cell(0, 0), new Cell(1, 1), Move.MoveType.ORDINARY, 7);
        bot.getGame().connectingPlayer("player1");
        bot.getGame().connectingPlayer("player2");
        bot.getGame().gameStarted(field);
        bot.getGame().getPlayerNames().put("player1", player);
        bot.getGame().setNextPlayerToAct(1);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> bot.getPlayerAction(move, "player1"));
        assertEquals("Сейчас не ход игрока: player1", exception.getMessage());
    }
    @Test
    void testGetPlayerAction_InvalidMove() {
        Player player_ = new Player(1, "player2");
        Move move = new Move(new Cell(0, 0), new Cell(8, 8), Move.MoveType.ORDINARY, 48); // неверный ход

        bot.getGame().connectingPlayer("player1");
        bot.getGame().connectingPlayer("player2");
        bot.getGame().gameStarted(field);
        bot.getGame().getPlayerNames().put("player1", player);

        assertTrue(bot.getGame().getPlayerNames().containsKey("player1"));

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> bot.getPlayerAction(move, "player1"));
        assertEquals("Недопустимый 'ORDINARY' ход: " + move, exception.getMessage());
    }

    @Test
    void testGameEnded() {
        assertDoesNotThrow(() -> bot.gameEnded("player1"));
    }

    @Test
    void testEndGameSession() {
        assertDoesNotThrow(() -> bot.endGameSession());
    }

    @Test
    void testGetAnswer() {
        Answer answer = bot.getAnswer(field);
        assertNotNull(answer);
        assertEquals(new Move(new Cell(0, 0), new Cell(1, 1), Move.MoveType.ORDINARY, 7), answer.getMove());
    }
}
