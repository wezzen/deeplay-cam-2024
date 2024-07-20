package io.deeplay.camp.engine.entities;

import io.deeplay.camp.engine.entities.domain.GameStates;
import io.deeplay.camp.engine.entities.domain.GameTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Field field;
    private Player player1;
    private Player player2;
    private ArrayList<Player> players;

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
    void testAddValidMove() {
        Game game = new Game(field, players, GameTypes.HumanVsBot);
        Move move = new Move(new Cell(0, 0, new Fleet(new ArrayList<>(), new Cell(0, 0))), new Cell(1, 1), Move.MoveType.ORDINARY);
        Move.MoveStatus status = game.addMove(move);
        assertEquals(Move.MoveStatus.DONE, status);
        assertNotNull(game.getAllGameMoves());
        assertEquals(1, game.getAllGameMoves().size());
        assertEquals(move, game.getAllGameMoves().get(0));
    }

    @Test
    void testAddInvalidMove() {
        Game game = new Game(field, players, GameTypes.HumansHuman);
        Move move = new Move(new Cell(9, 9, new Fleet(new ArrayList<>(), new Cell(9, 9))), new Cell(10, 10), Move.MoveType.ORDINARY);
        Move.MoveStatus status = game.addMove(move);
        assertEquals(Move.MoveStatus.ILLEGAL_MOVE, status);
        assertNotNull(game.getAllGameMoves());
        assertEquals(0, game.getAllGameMoves().size());
    }

}
