package io.deeplay.camp.game.entites;

import java.util.*;

/**
 * Класс игрового поля
 */
public class Field {

    private final int size;
    private Cell[][] board;

    private final List<Planet> planets;

    public void updateField() {
    }

    public Field(final int size) {
        this.size = size;
        this.planets = new ArrayList<>();
        generateField(size);
    }

    /**
     * Конструктор копирования для игрового поля.
     *
     * Создает новое игровое поле, являющееся глубокой копией переданного поля.
     * Копируются все клетки и планеты, создавая новые объекты, чтобы изменения
     * в новом поле не влияли на исходное.
     *
     * @param other поле, которое нужно скопировать
     */
    public Field(final Field other) {
        this.size = other.size;
        this.board = new Cell[other.size][other.size];
        this.planets = new ArrayList<>(other.planets.size());
        Map<Planet, Planet> planetMap = new HashMap<>();

        // Глубокое копирование поля
        for (int i = 0; i < other.size; i++) {
            for (int j = 0; j < other.size; j++) {
                Cell otherCell = other.board[i][j];
                if (otherCell != null) {
                    Planet otherPlanet = otherCell.getPlanet();
                    if (otherPlanet != null) {
                        Planet newPlanet = planetMap.computeIfAbsent(otherPlanet, p -> {
                            Planet copy = new Planet(p);
                            planets.add(copy);
                            return copy;
                        });
                        this.board[i][j] = new Cell(i, j, newPlanet);
                        newPlanet.setCell(this.board[i][j]);
                    } else {
                        this.board[i][j] = new Cell(i, j);
                    }
                } else {
                    this.board[i][j] = null;
                }
            }
        }
    }

    public int getSize() {
        return size;
    }

    public Cell[][] getBoard() {
        return board;
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    /**
     * Простая генерация зеркальной карты
     *
     * @param size размер поля
     */
    private void generateField(final int size) {
        Random random = new Random();
        board = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= i; j++) {
                if (random.nextInt(2) == 1) {
                    int temp = 100 + random.nextInt(13) * 50;
                    Planet newPlanet = new Planet(temp);
                    board[i][j] = new Cell(i, j, newPlanet);
                    newPlanet.setCell(board[i][j]);
                    planets.add(newPlanet);
                    if (i != j) {
                        Planet newPlanet2 = new Planet(temp);
                        board[j][i] = new Cell(j, i, newPlanet2);
                        newPlanet2.setCell(board[j][i]);
                        planets.add(newPlanet2);
                    }
                } else {
                    board[i][j] = new Cell(i, j);
                    if (i != j) {
                        board[j][i] = new Cell(j, i);
                    }
                }
            }
        }
    }

    /**
     * Проверка, принадлежат ли все планеты одному игроку.
     *
     * @return true, если все планеты принадлежат одному игроку, иначе false.
     */
    public boolean isGameOver() {
        Player owner = planets.getFirst().getOwner();
        if (owner == null) {
            return false;
        }
        for (Planet planet : planets) {
            if (!(planet.getOwner().equals(owner))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Выявление победителя сессии
     *
     * @return Player, который является владельцем всех планет на поле
     */
    public String isWinner() {
        if (isGameOver()) {
            return planets.getFirst().getOwner().getName();
        } else return ("победитель не существует");
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Field field)) return false;
        return size == field.size && Arrays.deepEquals(board, field.board);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.deepHashCode(board);
        return result;
    }
}
