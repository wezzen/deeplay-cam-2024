package io.deeplay.camp.game.entities;

import io.deeplay.camp.game.domain.GalaxyListener;
import io.deeplay.camp.game.domain.GameTypes;
import io.deeplay.camp.game.entites.*;
import io.deeplay.camp.game.entites.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

//todo переделать и добавить тесты сейчас заглушка для CI
class GameTest {

    private Player player1;
    private Player player2;
    private ArrayList<Player> players;
    Game game;

    @Test
    void startGameSessionTest() {
        Field field0 = new Field(10);
        player1 = new Player(0, "Player 1");
        player2 = new Player(1, "Player 2");
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        game = new Game(field);
        game.startGameSession("test", GameTypes.HumanVsBot);
        assertEquals(game.getId(), "test");
        assertEquals(game.getGameType(), GameTypes.HumanVsBot);
    }


    private final List<GalaxyListener> listeners =new ArrayList<>();
    private Game originalGame;
    private Game copiedGame;
    private Field field;

    @BeforeEach
    public void setUp() {
        // Создаем поле и начальные данные для теста
        field = new Field(10); // Предполагается, что конструктор принимает размер поля

        // Создаем и настраиваем оригинальную игру
        originalGame = new Game(field);
        copiedGame = new Game(originalGame);

        listeners.add(originalGame);
        listeners.add(copiedGame);

        for (final GalaxyListener listener : listeners) {
            listener.startGameSession(originalGame.getId(), originalGame.getGameType());
        }

        // Создаем игроков
        for (final GalaxyListener listener : listeners) {
            listener.connectingPlayer("Player1");
            listener.connectingPlayer("Player2");
        }


        // Устанавливаем начальные позиции
        for (final GalaxyListener listener : listeners) {
            listener.gameStarted(field);
        }

        // Добавляем ходы
        originalGame.getAllGameMoves().add(new Move(field.getBoard()[0][0], field.getBoard()[1][1], Move.MoveType.ORDINARY, 10));
        copiedGame.getAllGameMoves().add(new Move(field.getBoard()[0][0], field.getBoard()[1][1], Move.MoveType.ORDINARY, 10));
        originalGame.getAllGameMoves().add(new Move(field.getBoard()[1][1], field.getBoard()[2][2], Move.MoveType.CAPTURE, 20));
        copiedGame.getAllGameMoves().add(new Move(field.getBoard()[1][1], field.getBoard()[2][2], Move.MoveType.CAPTURE, 20));

        // Создаем флоты
        for (final GalaxyListener listener : listeners) {
            listener.createShips(List.of(Ship.ShipType.BASIC), "Player1");
            listener.createShips(List.of(Ship.ShipType.MEDIUM), "Player2");
        }
    }

    @Test
    public void testCopyConstructor() {
        // Проверяем, что идентификаторы и типы совпадают
        assertEquals(originalGame.getId(), copiedGame.getId());
        assertEquals(originalGame.getGameType(), copiedGame.getGameType());

        // Проверяем, что игроки скопированы корректно
        for (int i = 0; i < 2; i++) {
            Player originalPlayer = originalGame.players[i];
            Player copiedPlayer = copiedGame.players[i];
            assertNotNull(copiedPlayer);
            assertEquals(originalPlayer.getId(), copiedPlayer.getId());
            assertEquals(originalPlayer.getName(), copiedPlayer.getName());
            assertNotEquals(originalPlayer, copiedPlayer);
        }

        // Проверяем, что начальные позиции скопированы корректно
        for (Map.Entry<String, Cell> entry : originalGame.getPlayerStartPosition().entrySet()) {
            Cell originalCell = entry.getValue();
            Cell copiedCell = copiedGame.getPlayerStartPosition().get(entry.getKey());
            assertNotNull(copiedCell);
            assertEquals(originalCell.x, copiedCell.x);
            assertEquals(originalCell.y, copiedCell.y);
            assertNotEquals(originalCell, copiedCell);
        }

        // Проверяем, что ходы скопированы корректно
        assertEquals(originalGame.getAllGameMoves().size(), copiedGame.getAllGameMoves().size());
        for (int i = 0; i < originalGame.getAllGameMoves().size(); i++) {
            Move originalMove = originalGame.getAllGameMoves().get(i);
            Move copiedMove = copiedGame.getAllGameMoves().get(i);
            assertEquals(originalMove.moveType(), copiedMove.moveType());
            assertEquals(originalMove.startPosition().x, copiedMove.startPosition().x);
            assertEquals(originalMove.startPosition().y, copiedMove.startPosition().y);
            assertEquals(originalMove.endPosition().x, copiedMove.endPosition().x);
            assertEquals(originalMove.endPosition().y, copiedMove.endPosition().y);
            assertEquals(originalMove.cost(), copiedMove.cost());
        }

        // Проверяем, что поле скопировано корректно
        assertEquals(originalGame.getField().getSize(), copiedGame.getField().getSize());
        for (int x = 0; x < originalGame.getField().getSize(); x++) {
            for (int y = 0; y < originalGame.getField().getSize(); y++) {
                Cell originalCell = originalGame.getField().getBoard()[x][y];
                Cell copiedCell = copiedGame.getField().getBoard()[x][y];
                assertNotNull(copiedCell);
                assertEquals(originalCell.x, copiedCell.x);
                assertEquals(originalCell.y, copiedCell.y);
            }
        }

        // Проверяем, что состояние игроков корректно
        for (int i = 0; i < 2; i++) {
            Player originalPlayer = originalGame.players[i];
            Player copiedPlayer = copiedGame.players[i];
            assertNotSame(originalPlayer, copiedPlayer);
        }

        // Проверяем, что начальные позиции игроков правильно установлены
        //todo Не работает
        for (Map.Entry<String, Cell> entry : originalGame.getPlayerStartPosition().entrySet()) {
            Cell originalStartCell = entry.getValue();
            Cell copiedStartCell = copiedGame.getPlayerStartPosition().get(entry.getKey());
            assertNotSame(originalStartCell, copiedStartCell);
        }
    }
}
