package io.deeplay.camp.game.bots;

import io.deeplay.camp.game.domain.GameTypes;
import io.deeplay.camp.game.entites.*;
import io.deeplay.camp.game.utils.PointsCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BotTest {
    private static class TestBot extends Bot {
        protected TestBot(final String name, final Field field) {
            super(name, field);
        }

        public static class Factory extends BotFactory {

            public Factory() {
            }

            @Override
            public TestBot createBot(final String name, final Field field) {
                return new TestBot(name, field);
            }
        }

        @Override
        protected Move getMove() {
            Random random = new Random();
            List<Move> availableMoves;
            // Возвращаем тестовый ход
            final Field field = game.getField();  // Получаем копию поля
            final Cell[][] board = field.getBoard();
            final Player player = game.getPlayerByName(name);

            long fleetCount = Arrays.stream(board)
                    .flatMap(Arrays::stream)
                    .filter(cell -> cell.getFleet() != null && Objects.equals(cell.getFleet().getOwner().getName(), player.getName())).count();
            if (fleetCount == 0) {
                return new Move(null, null, Move.MoveType.SKIP, 0);
            }

            Cell startCell = Arrays.stream(board)
                    .flatMap(Arrays::stream)
                    .filter(cell -> cell.getFleet() != null && Objects.equals(cell.getFleet().getOwner().getName(), player.getName()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Нет клеток с флотом"));
            startCell.getFleet().addFleetMoves(field);

            availableMoves = startCell.getFleet().getFleetMoves();
            availableMoves.removeIf(move -> PointsCalculator.costMovement(move) > player.getTotalGamePoints());

            if (availableMoves.isEmpty()) {
                return new Move(null, null, Move.MoveType.SKIP, 0);
            }

            return availableMoves.get(random.nextInt(availableMoves.size()));
        }
    }

    private Field field;
    private Bot bot;
    private Player player;
    private Game game;

    @BeforeEach
    void setUp() {
        field = new Field(10);
        bot = new TestBot.Factory().createBot("player1", field);
        player = new Player(0, "player1");
        game = bot.game;
    }

    @Test
    void testStartGameSession() {
        assertDoesNotThrow(() -> bot.startGameSession("testGameId", GameTypes.HumanVsBot));
        assertDoesNotThrow(() -> bot.connectingPlayer("player1"));
        assertDoesNotThrow(() -> bot.connectingPlayer("player0"));
        assertDoesNotThrow(() -> bot.gameStarted(field));
    }

    @Test
    void testGetPlayerAction_ValidOrdinaryMove() {
        player = new Player(0, "player1");
        Player player2 = new Player(1, "player2");

        List<Ship.ShipType> startShips = new ArrayList<>();
        startShips.add(Ship.ShipType.BASIC);

        game.connectingPlayer(player.getName());
        game.connectingPlayer(player2.getName());
        game.gameStarted(field);
        game.getPlayerNames().put(player.getName(), player);
        game.getPlayerNames().put(player2.getName(), player2);

        game.createShips(startShips, player.getName());
        game.createShips(startShips, player2.getName());

        final Answer answer = bot.getAnswer(game.getField());

        if (answer.getShipList() != null) {
            game.createShips(answer.getShipList(), game.getNextPlayerToAct());
        }

        assertDoesNotThrow(() -> bot.getPlayerAction(answer.getMove(), player.getName()));
    }

    @Test
    void testGetPlayerAction_InvalidPlayer() {
        Move move = new Move(new Cell(0, 0), new Cell(1, 1), Move.MoveType.ORDINARY, 7);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> bot.getPlayerAction(move, "invalidPlayer"));
        assertEquals("Отсутствует игрок: invalidPlayer", exception.getMessage());
    }

    @Test
    void testGetPlayerAction_InvalidTurn() {
        Player player2 = new Player(1, "player2");
        Move move = new Move(new Cell(0, 0), new Cell(1, 1), Move.MoveType.ORDINARY, 7);

        game.connectingPlayer(player.getName());
        game.connectingPlayer(player2.getName());
        game.gameStarted(field);
        game.getPlayerNames().put(player.getName(), player);
        game.getPlayerNames().put(player2.getName(), player2);
        game.switchPlayerToAct();

        /*IllegalStateException exception = assertThrows(IllegalStateException.class, () -> bot.getPlayerAction(move, player.getName()));
        assertEquals("Сейчас не ход игрока: player1", exception.getMessage());*/
    }

    @Test
    void testGameEnded() {
        assertDoesNotThrow(() -> bot.gameEnded("player1"));
    }

    @Test
    void testEndGameSession() {
        assertDoesNotThrow(() -> bot.endGameSession());
    }
    /*private static class TestBot extends Bot {
        protected TestBot(final String name, final Field field) {
            super(name, field);
        }

        public static class Factory extends BotFactory {

            public Factory() {
            }

            @Override
            public TestBot createBot(final String name, final Field field) {
                return new TestBot(name, field);
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
    Player player = new Player(0, "player1");

    @BeforeEach
    void setUp() {
        field = new Field(10);
        bot = new TestBot.Factory().createBot("aaa", field);
    }

    @Test
    void testStartGameSession() {
        assertDoesNotThrow(() -> bot.startGameSession("testGameId", GameTypes.HumanVsBot));
        assertDoesNotThrow(() -> bot.connectingPlayer("player1"));
        assertDoesNotThrow(() -> bot.connectingPlayer("player0"));
        assertDoesNotThrow(() -> bot.gameStarted(field));
    }

    @Test
    void testGetPlayerAction_ValidOrdinaryMove() {
        Player player_ = new Player(1, "player2");
        Fleet fleet = new Fleet(field.getBoard()[0][0], player);
        Fleet fleet_ = new Fleet(field.getBoard()[0][1], player_);
        Ship ship = new Ship(Ship.ShipType.BASIC, fleet);
        Ship ship_ = new Ship(Ship.ShipType.BASIC, fleet_);
        Move move = new Move(field.getBoard()[0][0], field.getBoard()[1][1], Move.MoveType.ORDINARY, 7);
//        bot.getGame().connectingPlayer(player.getName());
//        bot.getGame().connectingPlayer(player_.getName());
//        bot.getGame().gameStarted(field);
//        bot.getGame().getPlayerNames().get("player1").fleetList.add(fleet);
//        bot.getGame().getPlayerNames().get("player2").fleetList.add(fleet_);
//        bot.getGame().setNextPlayerToAct(0);
//        assertDoesNotThrow(() -> bot.getPlayerAction(move, "player1"));

        //todo нормальные тесты
        assertThrows(IllegalArgumentException.class, () -> bot.getPlayerAction(move, "player1"));
    }

    @Test
    void testGetPlayerAction_InvalidPlayer() {
        Move move = new Move(new Cell(0, 0), new Cell(1, 1), Move.MoveType.ORDINARY, 7);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> bot.getPlayerAction(move, "invalidPlayer"));
        assertEquals("Отсутствует игрок: invalidPlayer", exception.getMessage());
    }

    @Test
    void testGetPlayerAction_InvalidTurn() {
        Player player_ = new Player(1, "player2");
        Move move = new Move(new Cell(0, 0), new Cell(1, 1), Move.MoveType.ORDINARY, 7);
//        bot.getGame().connectingPlayer("player1");
//        bot.getGame().connectingPlayer("player2");
//        bot.getGame().gameStarted(field);
//        bot.getGame().getPlayerNames().put("player1", player);
//        bot.getGame().setNextPlayerToAct(1);

//        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> bot.getPlayerAction(move, "player1"));
//        assertEquals("Сейчас не ход игрока: player1", exception.getMessage());
        //todo нормальные тесты
        assertThrows(IllegalArgumentException.class, () -> bot.getPlayerAction(move, "player1"));

    }

//    @Test
//    void testGetPlayerAction_InvalidMove() {
//        Player player_ = new Player(1, "player2");
//        Move move = new Move(new Cell(0, 0), new Cell(8, 8), Move.MoveType.ORDINARY, 48); // неверный ход
//
//        bot.getGame().connectingPlayer("player1");
//        bot.getGame().connectingPlayer("player2");
//        bot.getGame().gameStarted(field);
//        bot.getGame().getPlayerNames().put("player1", player);
//
//        assertTrue(bot.getGame().getPlayerNames().containsKey("player1"));
//
//        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> bot.getPlayerAction(move, "player1"));
//        assertEquals("Недопустимый 'ORDINARY' ход: " + move, exception.getMessage());
//    }

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
    }*/
}
