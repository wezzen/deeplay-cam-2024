package io.deeplay.camp.engine.entities;

import io.deeplay.camp.engine.entities.move.Move;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;

/**
 * Класс-представление сущности Игрок
 */
public class Player {
    /**
     * Хранит в себе переменные:
     * name - имя игрока
     * id - идентификатор
     * fleetList - список флотов в распоряжении
     * controlledPlanet - список захваченных планет
     * legalMoves - коллекция ходов для всех флотов в распоряжении
     */
    public final long id;
    private final String name;
    private int totalGamePoints;
    protected List<Fleet> fleetList;
    protected List<Planet> controlledPlanet;
    protected Collection<Move> legalMoves;

    public Player(final long id, final String name) {
        this.id = id;
        this.name = name;
        this.totalGamePoints = 0;
        this.fleetList = new ArrayList<>();
        this.controlledPlanet = new ArrayList<>();
        this.legalMoves = new ArrayList<>();
    }
    public boolean hasFleet(Fleet fleet) {
        return fleetList.contains(fleet);
    }
    public String getName() {
        return name;
    }

    public List<Fleet> getFleetList() {
        return fleetList;
    }

    public List<Planet> getControlledPlanet() {
        return controlledPlanet;
    }

    public Collection<Move> getLegalMoves() {
        return legalMoves;
    }

    public int getTotalGamePoints() {
        return totalGamePoints;
    }

    /**
     * Метод актуализирует количество очков игрока, пересчитывая их за захваченные планеты
     *
     * @return totalPoints
     */
    public int getCurrentGamePoints() {
        int totalPoints = 0;
        for (Planet planet : controlledPlanet) {
            totalPoints += planet.getPoints();
        }
        return totalPoints;
    }

    /**
     * Метод для удаления флота из общего списка
     *
     * @param fleet флот, который мы удаляем/проиграл сражение другому флоту
     */
    public void removeFleet(Fleet fleet) {
        this.fleetList.remove(fleet);
    }

    /**
     * Метод для удаления флота из общего списка
     *
     * @param fleet флот, который мы добавляем/создаем
     */
    public void addFleet(Fleet fleet) {this.fleetList.add(fleet);}

    /**
     * Метод фильтрует коллекцию ходов moves с помощью стрима stream(), оставляя только те ходы,
     * у которых координаты назначения совпадают с заданной клеткой.
     * Использует метод collect для сбора отфильтрованных ходов в неизменяемый список
     *
     * @param cell  клетка попадания
     * @param moves коллекция всех ходов
     * @return коллекция ходов в переданную клетку
     */
    Collection<Move> calculateAttacksOnTile(final Cell cell,
                                            final Collection<Move> moves) {
        return moves.stream()
                .filter(move -> move.endPosition().equals(cell)) //Либо move.getDestinationCoordinate() == tile
                .collect(collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id == player.id && totalGamePoints == player.totalGamePoints && Objects.equals(name, player.name) && Objects.equals(fleetList, player.fleetList) && Objects.equals(controlledPlanet, player.controlledPlanet) && Objects.equals(legalMoves, player.legalMoves);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, totalGamePoints, fleetList, controlledPlanet, legalMoves);
    }
}
