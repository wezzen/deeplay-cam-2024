package io.deeplay.camp.game.entites;

import io.deeplay.camp.game.utils.ValidationMove;

/**
 * Класс - ход, как record,
 * чтоб не закладывать, еще не продуманную, логику.
 * Record обеспечивает из коробки все требуемые методы (и даже больше)
 */
public record Move(Cell startPosition, Cell endPosition, MoveType moveType, int cost) {

    public void makeMove(final Player player) {
        Fleet fleet = startPosition.getFleet();
        Fleet enemyFleet = endPosition.getFleet();
        if (enemyFleet != null) { // на конечной точке есть другой флот
            if (!enemyFleet.getOwner().equals(player)) { // на конечной точке соперник
                fleet.fleetsClash(enemyFleet, player, enemyFleet.getOwner());
                if (player.getFleetList().contains(fleet)) {
                    setFleetOnPosition(endPosition, fleet);
                    clearFleetFromPosition(startPosition);
                } else {
                    clearFleetFromPosition(startPosition);
                }
            } else { // на конечной точке корабль игрока
                enemyFleet.addShipsIntoFleet(fleet.getShipList());
                player.removeFleet(fleet);
                clearFleetFromPosition(startPosition);
            }
        } else { // конечная точка свободна
            setFleetOnPosition(endPosition, fleet);
            clearFleetFromPosition(startPosition);
        }
        if (endPosition.planet != null && endPosition.getFleet().getOwner().equals(player)) { // ситуация, когда флот игрока уцелел и на конечной точке есть планета
            if (ValidationMove.isCapturePlanet(endPosition.getFleet().getFleetPower(), endPosition.planet.points))
                capturePlanet(player, endPosition.planet);
        }
    }

    public void capturePlanet(Player player, Planet planet) {
        player.controlledPlanet.add(planet);
        planet.setOwner(player);
        planet.isCaptured();
    }

    public void makeAttack(final Player player) {
        Fleet fleet = startPosition.getFleet();
        Fleet enemyFleet = endPosition.getFleet();
        if (endPosition.planet != null) {
            int enemyFleetPower = enemyFleet != null ? enemyFleet.getFleetPower() : 0;
            if (ValidationMove.isCapturePlanet(startPosition.getFleet().getFleetPower(), endPosition.planet.points + enemyFleetPower))
                capturePlanet(player, endPosition.planet);
        }
        if (enemyFleet != null) {
            fleet.fleetsClash(enemyFleet, player, enemyFleet.getOwner());
            if (player.getFleetList().contains(fleet)) {
                clearFleetFromPosition(endPosition);
            } else {
                clearFleetFromPosition(startPosition);
            }
        }
    }
    private void setFleetOnPosition(final Cell position, final Fleet fleet) {
        fleet.setFleetPosition(position);
        position.setFleet(fleet);
    }

    private void clearFleetFromPosition(final Cell position) {
        position.setFleet(null);
    }

    /**
     * Два типа хода:
     * ORDINARY - как перемещение по игровому полю
     * CAPTURE - атака на планету, захваченную противником
     */
    public enum MoveType {
        ORDINARY,
        CAPTURE
    }

    /**
     * Статус-флажок для контроля выполнения игроком хода:
     * DONE - выполнен
     * ILLEGAL_MOVE - ход не валидный, не выполнен
     * LEAVES_PLAYER_IN_CHECK - состояние проверки хода игрока
     */
    public enum MoveStatus {
        DONE,
        ILLEGAL_MOVE,
        LEAVES_PLAYER_IN_CHECK
    }

    @Override
    public String toString() {
        return "Start position = " + startPosition.toString() + " end position = " + endPosition.toString();
    }
}
