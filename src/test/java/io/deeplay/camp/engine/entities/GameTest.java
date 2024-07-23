package io.deeplay.camp.engine.entities;

import io.deeplay.camp.engine.entities.move.Move;
import io.deeplay.camp.engine.domain.GameStates;
import io.deeplay.camp.engine.domain.GameTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Field field;
    private Player player1;
    private Player player2;
    private ArrayList<Player> players;
    private static final int FLEET_POWER_JOIN = 200;
    private static final int FLEET_POWER_ATTACK = 100;

    @BeforeEach
    void setUp() {
        field = new Field(10);
        player1 = new Player(0, "Player 1");
        player2 = new Player(1, "Player 2");
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
    }

    @Test
    void testConstructor() {
        Game game = new Game(field, players, GameTypes.HumansHuman);
        assertEquals(GameStates.DEFAULT, game.getCurrentState());
        assertEquals(GameTypes.HumansHuman, game.getGameType());
        assertEquals(players, game.getPlayers());
    }

    @Test
    void testStartGame() {
        Game game = new Game(field, players, GameTypes.BotVsBot);
        game.startGame();
        assertEquals(GameStates.CHECK, game.getCurrentState());
    }

    @Test
    void testExecuteMove() {
        Game game = new Game(field, players, GameTypes.HumanVsBot);
        Move move = new Move(new Cell(0, 0), new Cell(1, 1), Move.MoveType.ORDINARY);

        game.executeMove(player1, move);

        assertEquals(GameStates.PRECEDENCE, game.getCurrentState());
    }

    @Test
    void testUpdateGameState() {
        Game game = new Game(field, players, GameTypes.HumansHuman);

        game.updateGameState();

        // Проверяем, что состояние игры изменилось на DEFAULT
        assertEquals(GameStates.DEFAULT, game.getCurrentState());
    }

    @Test
    void testIsGameOver() {
        Game game = new Game(field, players, GameTypes.HumansHuman);

        // Устанавливаем состояние игры на COMPLETE
        game.setCurrentState(GameStates.COMPLETE);
        assertTrue(game.isGameOver());

        // Устанавливаем состояние игры на DRAW
        game.setCurrentState(GameStates.DRAW);
        assertTrue(game.isGameOver());

        // Устанавливаем состояние игры на DEFAULT
        game.setCurrentState(GameStates.DEFAULT);
        assertFalse(game.isGameOver());
    }
    @Test
    void testMakeValidMove() {
        Game game = new Game(field, players, GameTypes.HumanVsBot);
        Move move = new Move(new Cell(0, 0, new Fleet(new ArrayList<>(), new Cell(0, 0))), new Cell(1, 1), Move.MoveType.ORDINARY);
        Move.MoveStatus status = game.makeMove(move, player1, player2);
        assertEquals(Move.MoveStatus.DONE, status);
        assertNotNull(game.getAllGameMoves());
        assertEquals(1, game.getAllGameMoves().size());
        assertEquals(move, game.getAllGameMoves().get(0));
    }

    @Test
    void testMakeValidMoveJoinFleets() {
        Game game = new Game(field, players, GameTypes.HumanVsBot);
        Ship ship = new Ship(Ship.ShipType.BASIC);
        List<Ship> shipList = new ArrayList<>();
        shipList.add(ship);
        Cell startCell = field.getBoard()[0][0];
        Cell endCell = field.getBoard()[0][2];
        Fleet fleet = new Fleet(shipList, startCell);
        Fleet fleet2 = new Fleet(shipList, endCell);
        startCell.setFleet(fleet);
        endCell.setFleet(fleet2);
        player1.addFleet(fleet);
        player1.addFleet(fleet2);
        Move move = new Move(startCell, endCell, Move.MoveType.ORDINARY);
        Move.MoveStatus status = game.makeMove(move, player1, player2);
        assertEquals(Move.MoveStatus.DONE, status);
        assertEquals(1, game.getAllGameMoves().size());
        assertEquals(move, game.getAllGameMoves().get(0));
        assertEquals(FLEET_POWER_JOIN, move.endPosition().getFleet().getFleetPower());
        assertNull(move.startPosition().getFleet());
    }

    @Test
    void testMakeValidMoveAttackFleets() {
        Game game = new Game(field, players, GameTypes.HumanVsBot);
        Ship ship = new Ship(Ship.ShipType.BASIC);
        List<Ship> shipList = new ArrayList<>();
        shipList.add(ship);
        Cell startCell = field.getBoard()[0][0];
        Cell endCell = field.getBoard()[0][2];
        Fleet fleet = new Fleet(shipList, startCell);
        Fleet fleet2 = new Fleet(shipList, endCell);
        startCell.setFleet(fleet);
        endCell.setFleet(fleet2);
        player1.addFleet(fleet);
        player2.addFleet(fleet2);
        Move move = new Move(startCell, endCell, Move.MoveType.ORDINARY);
        Move.MoveStatus status = game.makeMove(move, player1, player2);
        assertEquals(Move.MoveStatus.DONE, status);
        assertNotNull(game.getAllGameMoves());
        assertEquals(1, game.getAllGameMoves().size());
        assertEquals(move, game.getAllGameMoves().get(0));
        assertEquals(FLEET_POWER_ATTACK, move.endPosition().getFleet().getFleetPower());
        assertNull(move.startPosition().getFleet());
    }

}
